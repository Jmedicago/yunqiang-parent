package com.vgit.yunqiang.common.consts.msg;

import com.vgit.yunqiang.common.consts.ConstName;
import com.vgit.yunqiang.common.consts.ICodes;

public interface BisProductMsgConsts extends ICodes {

    public static final int BASE_CODE = 40000;

    @ConstName("库存不足")
    public static final int IN_A_SHORT_INVENTORY = 0 + BASE_CODE;

    @ConstName("已减到最小数量")
    public static final int AT_A_SHORT_INVENTORY = 1 + BASE_CODE;

}
