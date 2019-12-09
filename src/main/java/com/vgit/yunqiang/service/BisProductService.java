package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.model.ProductModel;
import com.vgit.yunqiang.pojo.BisProduct;
import com.vgit.yunqiang.pojo.BisProductMedia;
import com.vgit.yunqiang.pojo.BisProperty;
import com.vgit.yunqiang.pojo.BisSku;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BisProductService extends BaseService<BisProduct> {

    /**
     * 编辑商品信息
     *
     * @param product
     * @return
     */
    BisProduct saveOrUpdateProduct(BisProduct product);

    /**
     * 通过商品ID获取属性集
     *
     * @param id
     * @return
     */
    List<BisProperty> getProductProperties(Long id);

    /**
     * 获取商品的媒体列表
     *
     * @param id
     * @return
     */
    List<BisProductMedia> getProductMedias(Long id);

    /**
     * 删除指定的商品
     *
     * @param id
     */
    void deleteProduct(String id);

    /**
     * 通过商品ID获取sku属性集
     *
     * @param id
     * @return
     */
    List<BisProperty> getSkuProperties(Long id);

    /**
     * 获取商品的SKU列表
     *
     * @param id
     * @return
     */
    List<BisSku> getSkuList(Long id);

    /**
     * 保存SKU信息
     *
     * @param sku
     */
    void saveSku(BisSku sku);

    /**
     * 获取SKU信息
     *
     * @param id
     * @return
     */
    BisSku getSku(Long id);

    /**
     * 批量导入商品
     *
     * @param excelUrl
     * @return
     */
    Ret batch(String excelUrl) throws BisException;

    /**
     * 批量导出
     *
     * @param fileName
     * @param request
     * @return
     */
    String export(String fileName, HttpServletRequest request);
}
