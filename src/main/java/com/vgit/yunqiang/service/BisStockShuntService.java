package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.pojo.BisStockShunt;

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
     * 处理库存
     *
     * @param stockShunt
     * @throws BisException 库存不足
     */
    void shunt(BisStockShunt stockShunt) throws BisException;

}
