package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.query.ProductQuery;
import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Page;
import com.vgit.yunqiang.model.ProductModel;
import com.vgit.yunqiang.pojo.BisSku;

public interface BisSkuService extends BaseService<BisSku> {

    /**
     * 获取SKU商品列表
     *
     * @param query
     * @return
     */
    Page<BisSku> queryPage(ProductQuery query);

    /**
     * 恢复SKU锁定的库存
     *
     * @param skuId
     * @param amount
     */
    void recoverStock(Long skuId, Integer amount);

    /**
     * SKU出库
     *
     * @param skuId
     * @param amount
     */
    void outbound(Long skuId, Integer amount);

    /**
     * SKU入库
     *
     * @param id
     * @param amount
     * @return
     */
    void inbound(Long id, Integer amount);

    /**
     * 检查库存
     *
     * @param stockIds
     * @param sku
     * @throws BisException
     */
    void checkStock(String stockIds, BisSku sku) throws BisException;

    /**
     * 检查库存
     *
     * @param stockIds
     * @param sku
     * @param amount
     * @throws BisException
     */
    void checkStock(String stockIds, BisSku sku, Integer amount) throws BisException;

    /**
     * 减库存
     *
     * @param stockIds
     * @param sku
     * @param amount
     * @throws BisException
     */
    void reduceStock(String stockIds, BisSku sku, Integer amount) throws BisException;

    /**
     * ES 搜索
     *
     * @param query
     * @return
     */
    Page<ProductModel> es(BaseQuery query);

    /**
     * 根据商品ID删除
     *
     * @param productId
     */
    void delByProduct(Long productId);

}
