package com.vgit.yunqiang.service.impl;

import com.vgit.yunqiang.common.consts.bis.MediaTypeConsts;
import com.vgit.yunqiang.common.consts.bis.ProductStateConsts;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.common.service.impl.BaseServiceImpl;
import com.vgit.yunqiang.common.utils.IDUtils;
import com.vgit.yunqiang.mapper.BisProductMapper;
import com.vgit.yunqiang.mapper.BisSkuMapper;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.BisProductTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BisProductServiceImpl extends BaseServiceImpl<BisProduct> implements BisProductService {

    @Autowired
    private BisProductMapper mapper;

    @Autowired
    private BisSkuMapper skuMapper;

    @Autowired
    private BisProductTypeService bisProductTypeService;

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
                sku.setSkuCode(product.getCode() + seq);
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
