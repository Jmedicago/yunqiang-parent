package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.query.base.BaseQuery;
import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.model.ProductModel;
import com.vgit.yunqiang.pojo.BisSku;
import com.vgit.yunqiang.pojo.BisSkuProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BisSkuMapper extends BaseMapper<BisSku> {

    /**
     * 保存SKU的属性集
     *
     * @param skuPropertyList
     */
    void saveProperties(List<BisSkuProperty> skuPropertyList);

    /**
     * 通过SkuId删除SKU的属性集合
     *
     * @param id
     */
    void deleteProperties(Long id);

    /**
     * 获取指定商品的最大的SKU CODE
     *
     * @param productId
     * @return
     */
    String getMaxCode(Long productId);

    /**
     * 恢复SKU锁定的库存
     *
     * @param skuId
     * @param amount
     */
    void recoverStock(@Param("skuId") Long skuId, @Param("amount") Integer amount);

    /**
     * SKU确认出库
     *
     * @param skuId
     * @param amount
     */
    void outbound(@Param("skuId") Long skuId, @Param("amount") Integer amount);

    /**
     * 根据code查询商品SKU
     *
     * @param code
     * @return
     */
    BisSku getSkuByCode(String code);

    /**
     * 根据商品ID查询SKU列表
     *
     * @param code
     * @return
     */
    List<BisSku> getSkuListByCode(String code);

    /**
     * 修改SKU库存数量
     *
     * @param id
     * @param number
     */
    void changeNumber(@Param("id") Long id, @Param("number") Integer number);

    /**
     * 根据属性查找商品
     *
     * @param skuCode
     * @param skuCode
     * @param skuProperties
     * @return
     */
    BisSku selectByProperties(@Param("skuCode") String skuCode, @Param("skuProperties") String skuProperties);

    /**
     * ES 搜索
     *
     * @param query
     * @return
     */
    List<ProductModel> es(BaseQuery query);

    /**
     * ES 搜索记录数
     *
     * @param query
     * @return
     */
    int esTotal(BaseQuery query);

    /**
     * 根据商品ID删除
     *
     * @param productId
     */
    void delByProductIds(Long productId);
}
