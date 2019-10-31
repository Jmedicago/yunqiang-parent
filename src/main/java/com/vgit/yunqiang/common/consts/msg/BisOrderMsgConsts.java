package com.vgit.yunqiang.common.consts.msg;

import com.vgit.yunqiang.common.consts.ConstName;
import com.vgit.yunqiang.common.consts.ICodes;

public interface BisOrderMsgConsts extends ICodes {

    public static final int BASE_CODE = 30000;

    @ConstName("订单已锁定")
    public static final int ORDER_LOCKED = 0 + BASE_CODE;

    @ConstName("订单当前状态不能打印")
    public static final int ORDER_UN_PRINT = 1 + BASE_CODE;

}
