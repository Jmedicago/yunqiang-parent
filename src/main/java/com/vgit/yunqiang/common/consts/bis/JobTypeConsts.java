package com.vgit.yunqiang.common.consts.bis;

import com.vgit.yunqiang.common.consts.ConstName;

public class JobTypeConsts {

    @ConstName("待取消订单任务")
    public final static byte WAIT_ORDER_CANCEL_JOB = 0;

    @ConstName("待确认订单任务")
    public final static byte WAIT_ORDER_CONFIRM_JOB = 1;

}
