package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisCart;
import com.vgit.yunqiang.pojo.BisSku;

import java.util.List;
import java.util.Map;

public interface BisCartService extends BaseService<BisCart> {

    /**
     * 加入购物车
     *
     * @param userId
     * @param skuId
     * @param number
     */
    void add(Long userId, Long skuId, Integer number);

    /**
     * 用户删除购物车
     *
     * @param userId    用户id
     * @param cartIdArr 购物车id数组
     */
    void del(Long userId, Long[] cartIdArr);

    /**
     * 修改购物车条目数量
     *
     * @param userId
     * @param cartId
     * @param number
     */
    BisSku changeNumber(Long userId, Long cartId, Integer number);

    /**
     * 选中购物车
     *
     * @param userId
     * @param cartIdArr
     */
    void updateSelectCart(Long userId, Long[] cartIdArr);

    /**
     * 申购
     *
     * @param userId
     * @param skuId
     * @param number
     */
    void quick(Long userId, Long skuId, Integer number);

    /**
     * 统计用户当前购物车勾选的情况
     *
     * @param userId
     * @return goodsNumber
     * goodsTotalPrice
     * <p>
     * selectedGoodsNumber
     * selectedGoodsTotalPrice
     */
    Map<String, Object> calculate(Long userId);

    /**
     * 获取用户的所有购物车数据
     *
     * @param userId
     * @return
     */
    List<BisCart> getCarts(Long userId);

    /**
     * 统计列表的购物车信息
     *
     * @param cartList
     * @return goodsNumber
     * goodsTotalPrice
     * <p>
     * selectedGoodsNumber
     * selectedGoodsTotalPrice
     */
    Map<String, Object> statistic(List<BisCart> cartList);

    /**
     * 获取用户购物车的情况
     *
     * @param userId
     * @return goodsNumber
     * goodsTotalPrice
     * <p>
     * selectedGoodsNumber
     * selectedGoodsTotalPrice
     * <p>
     * data : List-BisCart
     */
    Map<String, Object> info(Long userId);

    /**
     * 获取用户购物车选中的情况
     *
     * @param userId
     * @return goodsNumber
     * goodsTotalPrice
     * <p>
     * selectedGoodsNumber
     * selectedGoodsTotalPrice
     * <p>
     * data : List-BisCart selected
     */
    Map<String, Object> selectedInfo(Long userId);

    /**
     * 删除购物车已购买部分的数据
     *
     * @param userId
     */
    void clearQuick(Long userId);

}
