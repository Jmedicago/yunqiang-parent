package com.vgit.yunqiang.mapper;

import com.vgit.yunqiang.common.service.BaseMapper;
import com.vgit.yunqiang.pojo.BisCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BisCartMapper extends BaseMapper<BisCart> {

    /**
     * 通过userId获取购物车中skuId的购物条目
     *
     * @param userId
     * @param skuId
     * @return
     */
    BisCart getByUserSku(@Param("userId") Long userId, @Param("skuId") Long skuId);

    /**
     * 用户删除购物车
     *
     * @param userId
     * @param cartIds
     */
    void delCartByUser(@Param("userId") Long userId, @Param("cartIds") Long[] cartIds);

    /**
     * 修改购物车条目数量
     *
     * @param userId
     * @param cartId
     * @param number
     */
    void changeNumber(@Param("userId") Long userId, @Param("cartId") Long cartId, @Param("number") Integer number);

    /**
     * 取消用户已选择的购物车所有条目
     *
     * @param userId
     */
    void cancelSelectAll(Long userId);

    /**
     * 选中购物车
     *
     * @param userId
     * @param cartIdArr
     */
    void updateSelectCart(Long userId, Long[] cartIdArr);

    /**
     * 获取用户购物车数据
     *
     * @param userId
     * @return
     */
    List<BisCart> getCarts(Long userId);

    /**
     * 删除购物车已购买部分的数据
     *
     * @param userId
     */
    void clearQuick(Long userId);

    /**
     * 获取用户购物车数量
     *
     * @param userId
     * @return
     */
    int getCartsTotal(Long userId);

    /**
     * 修改勾选状态
     *
     * @param userId
     * @param cartId
     * @param selected
     */
    void changeSelected(@Param("userId") Long userId, @Param("cartId") Long cartId, @Param("selected") Integer selected);

    /**
     * 清空购物车
     */
    void deleteAll(@Param("userId") Long userId);

    /**
     * 根据类目查询购物车数量
     *
     * @param productType
     * @return
     */
    Integer getTotalByProductType(@Param("productType") Long productType, @Param("userId") Long userId);

    /**
     * 根据SKUID移除购物车
     *
     * @param skuId
     */
    void delCartBySkuId(Long skuId);
}
