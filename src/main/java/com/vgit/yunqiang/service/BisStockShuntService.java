package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStockShunt;

import java.util.List;

public interface BisStockShuntService extends BaseService<BisStockShunt> {

    // 总仓
    String DEFAULT_STOCK = "1000";

    // 北仓
    String NORTH_STOCK = "1062";

    // 南仓
    String SOUTH_STOCK = "1050";

    /**
     * 提交商品分流信息
     *
     * @param stockShunt 分流订单
     */
    void submitShunt(BisStockShunt stockShunt);

    /**
     * 确认商品分流信息
     *
     * @param stockShunt 分流订单
     */
    void confirmShunt(BisStockShunt stockShunt);

    /**
     * 出仓
     *
     * @param orderId 订单ID
     * @throws BisException 库存不足
     */
    void checkOut(Long orderId) throws BisException;

    /**
     * 入仓
     *
     * @param stockShunt
     * @desc 默认入总仓
     */
    void checkIn(BisStockShunt stockShunt);

    /**
     * 入库
     *
     * @param skuId
     * @param amount
     */
    void checkIn(Long skuId, Integer amount);

    /**
     * 处理库存
     *
     * @param stockShunt
     * @throws BisException 库存不足
     */
    void shunt(BisStockShunt stockShunt) throws BisException;

    /**
     * 查询SKU库存量
     *
     * @param skuId
     * @return
     */
    List<BisStockShunt> getList(Long skuId);

    /**
     * 查看商品库存
     *
     * @param skuId
     * @param stockId
     * @return
     */
    BisStockShunt getSkuStock(Long skuId, Long stockId);

    /**
     * 检查库存
     *
     * @param skuId  需求商品
     * @param stockId 需求零售店
     * @param amount 需求数量
     * @return
     */
    boolean checkStock(Long skuId, Long stockId, Integer amount);

    /**
     * 根据订单信息查询剩余SKU库存
     *
     * @param skuId
     * @param orderId
     * @return
     */
    Integer getSkuStockByOrderId(Long skuId, Long orderId);
}
