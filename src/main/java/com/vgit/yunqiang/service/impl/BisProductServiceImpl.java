package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.MediaTypeConsts;
import com.vgit.yunqiang.common.consts.bis.ProductStateConsts;
import com.vgit.yunqiang.common.consts.bis.PropertyInputModeConsts;
import com.vgit.yunqiang.common.query.PropertyQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.*;
import com.vgit.yunqiang.mapper.BisProductMapper;
import com.vgit.yunqiang.mapper.BisSkuMapper;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.PictureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class BisProductServiceImpl extends BaseServiceImpl<BisProduct> implements BisProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BisProductServiceImpl.class);

    @Autowired
    private BisProductMapper mapper;

    @Autowired
    private BisSkuMapper skuMapper;

    @Autowired
    private BisProductTypeService bisProductTypeService;

    @Autowired
    private BisPropertyService bisPropertyService;

    @Autowired
    private BisPropertyOptionService bisPropertyOptionService;

    @Autowired
    private BisStockService bisStockService;

    @Autowired
    private ResourcesService resourcesService;

    @Override
    protected BaseMapper<BisProduct> getMapper() {
        return this.mapper;
    }

    /**
     * 公共的保存商品信息初始化方法
     *
     * @param product
     */
    private void initProduct(BisProduct product) {
        if (StringUtils.isBlank(product.getCode())) {
            String code = IDUtils.generateProductCode();
            product.setCode(code);
        }
        product.setState((int) ProductStateConsts.PRODUCT_STATE_ON_SALE);
        product.setCreateTime(System.currentTimeMillis());
        product.setUpdateTime(System.currentTimeMillis());
    }

    @Override
    public BisProduct saveOrUpdateProduct(BisProduct product) {
        if (product.getId() == null) {
            this.initProduct(product);
            this.mapper.savePart(product);
            // 保存属性集
            this.storeProperties(product);
            // 保存商品图片
            this.storeMedia(product);
        } else {
            this.mapper.updatePart(product);
            // 保存属性集
            this.storeProperties(product);
            // 保存商品图片
            this.storeMedia(product);
        }
        return product;
    }

    @Override
    public List<BisProperty> getProductProperties(Long id) {
        return this.mapper.getProperties(id);
    }

    @Override
    public List<BisProductMedia> getProductMedias(Long id) {
        return this.mapper.getMedias(id);
    }

    @Override
    public void deleteProduct(String ids) {
        String[] idArr = ids.split(",");
        Long[] idLongs = new Long[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            Long id = Long.valueOf(idArr[i]);
            this.mapper.delete(id);
            idLongs[i] = id;
        }
    }

    @Override
    public List<BisProperty> getSkuProperties(Long id) {
        return this.mapper.getSkuProperties(id);
    }

    @Override
    public List<BisSku> getSkuList(Long id) {
        return this.mapper.getSkuList(id);
    }

    @Override
    public void saveSku(BisSku sku) {
        // 获取SKU的属性集合格式字符串
        List<String> skuPropertyStrList = new ArrayList<>();
        for (BisSkuProperty skuProperty : sku.getSkuPropertyList()) {
            skuProperty.setSkuId(sku.getId());

            String propName = skuProperty.getPropName();
            if (StringUtils.isBlank(propName)) {
                propName = "";
            } else {
                propName = propName.replace(":", "：").replace("_", "-");
            }
            String propValue = skuProperty.getValue();
            if (StringUtils.isBlank(propValue)) {
                propValue = "";
            } else {
                propValue = propValue.replace(":", "：").replace("_", "-");
            }

            String propertyStr = skuProperty.getPropId() + ":" + propName + ":" + skuProperty.getOptionId() + ":"
                    + propValue;
            skuPropertyStrList.add(propertyStr);
        }

        if (skuPropertyStrList.size() > 0) {
            String skuProperties = StringUtils.join(skuPropertyStrList, "_");
            // SKU的属性
            sku.setSkuProperties(skuProperties);
        }

        if (null != sku.getId()) {
            sku.setUpdateTime(System.currentTimeMillis());
            this.skuMapper.updatePart(sku);
        } else {
            // 新增
            String maxCode = this.skuMapper.getMaxCode(sku.getProductId());
            String seq = "01";
            if (StringUtils.isNotBlank(maxCode)) {
                seq = maxCode.substring(maxCode.length() - 2);
                int seqNumber = Integer.parseInt(seq);
                seqNumber++;
                if (seqNumber <= 9) {
                    seq = "0" + seqNumber;
                } else {
                    seq = "" + seqNumber;
                }
            }
            BisProduct product = this.get(sku.getProductId());
            if (null != product.getName()) {
                sku.setSkuName(product.getName());
            }
            if (null != product) {
                //sku.setSkuCode(product.getCode() + seq);
            	sku.setSkuCode(product.getCode());
            }
            if (null == (sku.getAvailableStock())) {
                sku.setAvailableStock(0);
            }
            if (null == sku.getFrozenStock()) {
                sku.setFrozenStock(0);
            }
            sku.setPushStockTime(System.currentTimeMillis());
            sku.setCreateTime(System.currentTimeMillis());
            sku.setUpdateTime(System.currentTimeMillis());
            this.skuMapper.save(sku);
        }

        List<BisSkuProperty> skuPropertyList = sku.getSkuPropertyList();
        for (BisSkuProperty skuProperty : skuPropertyList) {
            skuProperty.setSkuId(sku.getId());
        }

        this.skuMapper.deleteProperties(sku.getId());
        this.skuMapper.saveProperties(skuPropertyList);
    }

    @Override
    public BisSku getSku(Long id) {
        return this.skuMapper.get(id);
    }

    @Override
    public Ret batch(String excelUrl) {
        List<List<Map<String, String>>> sheets = null;
        List<Map<String, PictureData>> images = null;
        boolean isUrl = true;

        try {
            // 获取图片
            images = ExcelUtils.getSheetPictures(excelUrl, isUrl);
            // 获取Sheets
            sheets = ExcelUtils.readExcel(excelUrl, isUrl, 0);

            int start = 1; // 图片起始位置
            // 遍历
            List<Map<String, String>> list = sheets.get(0);
            for (int curr = 0; curr < list.size(); curr++) {
                BisProduct bisProduct = new BisProduct();
                BisSku bisSku = new BisSku();
                bisProduct.setName("newNode");
                initProduct(bisProduct);
                this.savePart(bisProduct);
                for (Map.Entry<String, String> entry : list.get(curr).entrySet()) {
                    if ("类别".equals(entry.getKey())) { // 格式：工厂鞋.EVA鞋.绑带鞋
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            String[] names = entry.getValue().split("\\.");
                            BisProductType bisProductType = getProductType(1000L, names);
                            if (bisProductType != null) {
                                bisProduct.setProductType(bisProductType.getId());
                            }
                        }
                    }
                    if ("权属".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            BisStock bisStock = this.bisStockService.getStockByName(entry.getValue());
                            if (bisStock != null) {
                                bisProduct.setStock(bisStock.getId());
                            }
                        }

                    }
                    if ("商品名".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisProduct.setName(entry.getValue());
                        }
                    }
                    if ("图示".equals(entry.getKey())) {
                        for (Map<String, PictureData> image : images) {
                            String key = String.valueOf(curr + start);
                            PictureData pic = image.get(key);
                            if (pic != null) {
                                String ext = pic.suggestFileExtension();
                                byte[] data = pic.getData();
                                // 上传图片
                                Map<String, Object> map = this.resourcesService.upload(ext, data);
                                int state = (int) map.get("error");
                                if (state == 0) {
                                    // 保存商品素材
                                    List<BisProductMedia> productMedias = new ArrayList<>();
                                    String resource = (String) map.get("url");
                                    BisProductMedia media = new BisProductMedia();
                                    media.setProductId(bisProduct.getId());
                                    media.setResource(resource);
                                    productMedias.add(media);
                                    bisProduct.setProductMediaList(productMedias);
                                }

                            }
                        }
                    }
                    if ("属性".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) { // 颜色：红色V，码段：37-41#
                            PropertyQuery query = new PropertyQuery();
                            query.setProductType(bisProduct.getProductType());
                            List<BisProperty> properties = this.bisPropertyService.getProperties(query); // 属性集合
                            List<Long> ids = new ArrayList<Long>(); // 商品属性集合
                            List<BisSkuProperty> skuPropertyList = new ArrayList<>(); // SKU属性集合
                            for (BisProperty bisProperty : properties) {
                                List<Map<String, String>> propList = this.getPropertyMapByStr(entry.getValue());
                                if (!propList.isEmpty()) {
                                    for (Map<String, String> propMap : propList) {
                                        for (Map.Entry<String, String> propEntry : propMap.entrySet()) {
                                            if (bisProperty.getName().equals(propEntry.getKey())) {
                                                ids.add(bisProperty.getId());

                                                if (PropertyInputModeConsts.PROPERTY_INPUT_MODE_TEXT == bisProperty.getInputMode()) { // 文本框
                                                    BisSkuProperty skuProperty = new BisSkuProperty();
                                                    skuProperty.setPropId(bisProperty.getId());
                                                    skuProperty.setPropName(bisProperty.getName());
                                                    skuProperty.setOptionId(0L);
                                                    skuProperty.setValue(propEntry.getValue());
                                                    skuPropertyList.add(skuProperty);
                                                } else if (PropertyInputModeConsts.PROPERTY_INPUT_MODE_SELECT == bisProperty.getInputMode()) { // 选择框
                                                    BisSkuProperty skuProperty = new BisSkuProperty();
                                                    skuProperty.setPropId(bisProperty.getId());
                                                    skuProperty.setPropName(bisProperty.getName());
                                                    List<BisPropertyOption> bisPropertyOptionList = this.bisPropertyService.getOptions(bisProperty.getId());
                                                    for (BisPropertyOption bisPropertyOption : bisPropertyOptionList) {
                                                        if (propEntry.getValue().equals(bisPropertyOption.getOptionValue())) {
                                                            skuProperty.setOptionId(bisPropertyOption.getId());
                                                            skuProperty.setValue(bisPropertyOption.getOptionValue());
                                                        }
                                                    }
                                                    skuPropertyList.add(skuProperty);
                                                }
                                                this.bisPropertyService.getOptions(bisProperty.getId());
                                            }
                                        }
                                    }
                                }
                            }
                            Long[] array = new Long[ids.size()];
                            ids.toArray(array);
                            bisProduct.setProperties(array);
                            bisSku.setSkuPropertyList(skuPropertyList);
                        }
                    }
                    if ("货品编号".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisProduct.setCode(entry.getValue());
                        }
                    }
                    if ("包装形态".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            String pack = entry.getValue().split("\\.")[0];
                            bisSku.setPack(Integer.parseInt(pack));
                        }
                    }
                    if ("单件体积".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setVolume(Double.valueOf(entry.getValue()));
                        }
                    }
                    if ("单体成本".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setCostPrice(Double.valueOf(entry.getValue()) * 100);
                        }
                    }
                    if ("建议批发价".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setMarketPrice(Double.valueOf(entry.getValue()) * 100);
                        }
                    }
                    if ("货柜编号".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setContainer(entry.getValue());
                        }
                    }
                    if ("利润".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setProfit(Double.valueOf(entry.getValue()));
                        }
                    }
                    if ("供应商".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            bisSku.setSupplier(entry.getValue());
                        }
                    }
                    if ("待入库件数".equals(entry.getKey())) {
                        if (StringUtils.isNotBlank(entry.getValue())) {
                            String availableStock = entry.getValue().split("\\.")[0];
                            bisSku.setAvailableStock(Integer.valueOf(availableStock));
                        }
                    }
                }
                // 保存商品信息
                LOGGER.info("Excel导入的商品信息:{}", bisProduct.toString());
                String remark = "方式：Excel批量导入；\n时间：" + TimeUtils.dateFormat(new Date()) + "\n明细：" + bisProduct.toString();
                bisProduct.setRemark(remark);
                List<BisProductMedia> mediaList = bisProduct.getProductMediaList();
                if (mediaList != null && mediaList.size() > 0) {
                    bisSku.setSkuMainPic(mediaList.get(0).getResource());
                }
                
                // 检查商品是否存在
                if (this.hasProduct(bisProduct.getCode())) { // 存在，增加库存
                	BisSku sku = this.skuMapper.getSkuByCode(bisProduct.getCode());
                	if (sku != null) { 
                		BisSku newSkul = new BisSku();
                		newSkul.setId(sku.getId());
                		newSkul.setAvailableStock(sku.getAvailableStock() + bisSku.getAvailableStock());
                		this.skuMapper.updatePart(newSkul);
                		this.mapper.delProductByName("newNode");
                	}
                } else { // 不存在，新增商品
                	this.saveOrUpdateProduct(bisProduct);
                    bisSku.setProductId(bisProduct.getId());
                    bisSku.setPushStockTime(System.currentTimeMillis());
                    this.saveSku(bisSku);
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Ret.me();
    }

    private boolean hasProduct(String code) {
    	BisProduct product = this.mapper.getProducByCode(code);
    	if (product != null) {
    		return true;
    	}
		return false;
	}

	private List<Map<String, String>> getPropertyMapByStr(String value) { // 颜色：红色V，码段：37-41#
        if (StringUtils.isNotBlank(value)) {
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            // 格式化
            value = value.replace("：", ":").replace("，", ",");
            String[] properties = value.split(",");
            for (String prop : properties) {
                String[] opts = prop.split(":");
                Map<String, String> kv = new HashMap<String, String>();
                kv.put(opts[0], opts[1]);
                list.add(kv);
            }
            return list;
        }
        return null;
    }

    private BisProductType getProductType(Long parentId, String... names) {
        BisProductType productType = null;
        boolean flag = true;
        int index = 0;
        while (flag) {
            if (index < names.length) {
                String name = names[index];
                productType = this.bisProductTypeService.getProductTypeByNameAndParentId(name, parentId);
                parentId = productType.getId();
                index++;
            } else {
                flag = false;
            }
        }
        return productType;
    }

    /**
     * 保存商品属性集合
     *
     * @param product
     */
    private void storeProperties(BisProduct product) {
        this.mapper.deleteProperties(product.getId());
        List<Map<String, Long>> mapList = createProductPropertyList(product);
        if (mapList.size() > 0) {
            this.mapper.saveProperties(mapList);
        }
    }

    /**
     * 创建商品的属性集合MAP，用于批量添加
     *
     * @param product
     * @return
     */
    private List<Map<String, Long>> createProductPropertyList(BisProduct product) {
        List<Map<String, Long>> list = new ArrayList<Map<String, Long>>();
        Long[] properties = product.getProperties();
        if (properties != null) {
            for (Long propertyId : properties) {
                Map<String, Long> map = new HashMap<String, Long>();
                map.put("productId", product.getId());
                map.put("propertyId", propertyId);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 保存商品图片
     *
     * @param product
     */
    private void storeMedia(BisProduct product) {
        this.mapper.deleteMedias(product.getId());
        List<BisProductMedia> productMediaList = product.getProductMediaList();
        if (null != productMediaList) {
            for (BisProductMedia productMedia : productMediaList) {
                productMedia.setMediaType((int) MediaTypeConsts.IMAGE);
                productMedia.setProductId(product.getId());
            }
            if (productMediaList != null && productMediaList.size() > 0) {
                this.mapper.saveMedias(productMediaList);
            }
        }
    }

}
