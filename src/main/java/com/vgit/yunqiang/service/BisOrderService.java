package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.service.BaseService;
import com.vgit.yunqiang.common.utils.Ret;
import com.vgit.yunqiang.pojo.BisOrder;

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

}
