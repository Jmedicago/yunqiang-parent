package com.vgit.yunqiang.service;

import com.vgit.yunqiang.common.query.QuartzJobInfo;

public interface JobRunnerService {

    /**
     * 取消订单
     *
     * @param info
     */
    void cancelOrder(QuartzJobInfo info);

    /**
     * 提交订单
     *
     * @param info
     */
    void confirmOrder(QuartzJobInfo info);

}
