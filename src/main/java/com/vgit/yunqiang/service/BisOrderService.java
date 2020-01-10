package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.exception.BisException;
import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BisOrderService extends BaseService<BisOrder> {

    /**
     * 生成订单
     *
     * @param formOrder 订单数据
     * @param billId    发票id
     * @return
     */
    Ret create(BisOrder formOrder, Long billId);

    /**
     * 通过订单ID获取订单
     *
     * @param id
     * @return
     */
    BisOrder getById(Long id);

    /**
     * 取消订单
     *
     * @param orderId
     */
    void cancel(Long orderId);

    /**
     * 发货确认
     *
     * @param orderIdArr
     */
    void sendShip(Long[] orderIdArr);

    /**
     * 确认收货（订单完成）
     *
     * @param orderId
     */
    void confirmFinish(Long orderId);

    /**
     * 确认发货
     *
     * @param orderId
     * @return
     */
    Ret sendShip(Long orderId);

    /**
     * 打印出库单
     *
     * @param orderId
     * @return
     */
    BisOrder print(Long orderId) throws BisException;

    /**
     * 打印前检查
     *
     * @param id
     * @return
     */
    Ret checkPrint(Long id);

    /**
     * 是否存在未评价订单
     *
     * @param orderId
     * @return
     */
    boolean hasNotComment(Long orderId);

    /**
     * 仓管员新增商品到指定订单
     *
     * @param orderId
     * @param splitToLong
     */
    void addToOrder(Integer o, Long orderId, Long[] splitToLong);

    void updateState(Long orderId, Integer state);

    /**
     * 获取当日下单金额
     *
     * @param stockId
     * @return
     */
    double getCurDailyTackOrder(Long stockId);
}
