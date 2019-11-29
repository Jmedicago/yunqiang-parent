package com.vgit.yunqiang.controller;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.controller.consts.ControllerConsts;
import com.vgit.yunqiang.pojo.*;
import com.vgit.yunqiang.service.BisProductService;
import com.vgit.yunqiang.service.BisSkuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/" + BisProductController.DOMAIN)
public class BisProductController {

    public static final String DOMAIN = "product";

    @Autowired
    private BisProductService bisProductService;

    @Autowired
    private BisSkuService bisSkuService;

    @RequestMapping(ControllerConsts.URL_INDEX)
    public String index() {
        return DOMAIN + ControllerConsts.VIEW_INDEX;
    }

    @RequestMapping(ControllerConsts.URL_JSON)
    @ResponseBody
    public Page<BisProduct> json(ProductQuery query) {
        return this.bisProductService.queryPage(query);
    }

    @RequestMapping(ControllerConsts.URL_EDIT)
    public String edit(Long id, Model model) {
        if (id != null) {
            BisProduct bisProduct = this.bisProductService.get(id);
            // 获取商品类型并返回给视图
            model.addAttribute("productType", bisProduct.getProductType());

            //获取商品属性集合ID字符串集合
            List<BisProperty> properties = this.bisProductService.getProductProperties(bisProduct.getId());
            StringBuffer sb = new StringBuffer();
            for (BisProperty property : properties) {
                sb.append(",").append(property.getId());
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(0);
            }
            model.addAttribute("selectProperties", sb.toString());

            //获取商品的图片KEY字符串集合
            List<BisProductMedia> productMedias = this.bisProductService.getProductMedias(bisProduct.getId());
            StringBuilder resources = new StringBuilder("");
            if (productMedias != null) {
                for (BisProductMedia productMedia : productMedias) {
                    String resource = productMedia.getResource();
                    resources.append(",").append(resource);
                }
            }
            if (resources.length() > 0) {
                resources.deleteCharAt(0);
            }
            model.addAttribute("resources", resources.toString());
            // 商品信息
            model.addAttribute("bisProduct", bisProduct);
        }
        return DOMAIN + ControllerConsts.VIEW_EDIT;
    }

    /**
     * 保存或修改商品信息
     *
     * @param product    商品信息
     * @param properties 商品属性ID集合
     * @param resources  商品的图片集合
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresRoles(value = {"admin", "product"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_STORE)
    @ResponseBody
    public Ret store(BisProduct product, Long[] properties, String resources) throws UnsupportedEncodingException {
        product.setProperties(properties);
        if (StringUtils.isNotBlank(resources)) {
            List<BisProductMedia> medias = new ArrayList<>();
            String[] resourceArr = resources.split(",");
            for (String resource : resourceArr) {
                BisProductMedia productMedia = new BisProductMedia();
                productMedia.setCreateTime(System.currentTimeMillis());
                productMedia.setUpdateTime(System.currentTimeMillis());
                productMedia.setResource(resource);
                medias.add(productMedia);
            }
            product.setProductMediaList(medias);
        }
        // 更新商品信息
        this.bisProductService.saveOrUpdateProduct(product);
        return Ret.me();
    }

    @RequiresRoles(value = {"admin", "product"}, logical = Logical.OR)
    @RequestMapping(ControllerConsts.URL_DELETE)
    @ResponseBody
    public Ret delete(String id) {
        if (StringUtils.isBlank(id)) {
            return Ret.me().setSuccess(false).setInfo("无效的ID");
        }
        this.bisProductService.deleteProduct(id);
        return Ret.me().setSuccess(true);
    }

    /**
     * 显示商品的SKU管理页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/skus")
    public String skus(Long id, Model model) {
        BisProduct o = this.bisProductService.get(id);
        model.addAttribute("product", o);

        // 获取商品对应的所有SKU属性集
        List<BisProperty> skuProperties = this.bisProductService.getSkuProperties(id);
        model.addAttribute("skuProperties", skuProperties);

        // 获取商品对应的所有SKU
        List<BisSku> skuList = this.bisProductService.getSkuList(id);
        model.addAttribute("skuList", skuList);
        return DOMAIN + "/skus";
    }

    /**
     * 存储商品的SKU列表
     *
     * @param sku
     * @param propId
     * @param propName
     * @param optionId
     * @param optionValue
     * @return
     */
    @RequiresRoles(value = {"admin", "product"}, logical = Logical.OR)
    @RequestMapping("/storeSku")
    @ResponseBody
    public Ret storeSku(BisSku sku, Long[] propId, String[] propName, Long[] optionId,
                        String[] optionValue) {

        if (propId != null) {
            List<BisSkuProperty> skuPropertyList = new ArrayList<>();
            for (int i = 0; i < propId.length; i++) {
                BisSkuProperty skuProperty = new BisSkuProperty();
                skuProperty.setPropId(propId[i]);
                skuProperty.setPropName(propName[i]);
                skuProperty.setOptionId(optionId[i]);
                skuProperty.setValue(optionValue[i]);
                skuPropertyList.add(skuProperty);
            }
            sku.setSkuPropertyList(skuPropertyList);
        }

        if (sku.getMarketPrice() != null) {
            sku.setMarketPrice(sku.getMarketPrice() * 100);
        } else {
            sku.setMarketPrice(0d);
        }

        sku.setCostPrice(sku.getCostPrice() * 100);
        this.bisProductService.saveSku(sku);
        return Ret.me();
    }

    /**
     * 返回指定ID的SKU信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/sku/{id}")
    @ResponseBody
    public BisSku getSkuJson(@PathVariable(value = "id") Long id) {
        BisSku sku = this.bisProductService.getSku(id);
        return sku;
    }

    /**
     * Excel批量导入
     *
     * @return
     */
    @RequestMapping("/batch")
    @ResponseBody
    public Ret batch(String excelUrl, HttpServletRequest request) {
        try {
            return this.bisProductService.batch(excelUrl);
        } catch (BisException e) {
            return Ret.me().setSuccess(false).setCode(ICodes.FAILED).setInfo(e.getInfo());
        }
    }

}
