package com.vgit.yunqiang.common.consts.bis;

import com.vgit.yunqiang.common.consts.ConstName;

public class TradeStockStateConsts {

    @ConstName("待审核")
    public static byte WAIT_SHIP_AUDITING = 0;

    @ConstName("待发货")
    public static byte WAIT_SHIP_SEND = 1;

    @ConstName("已到货")
    public static byte SHIP_FINISH_TAKE = 2;

}
