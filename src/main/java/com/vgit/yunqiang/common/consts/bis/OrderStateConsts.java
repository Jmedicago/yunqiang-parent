package com.vgit.yunqiang.common.consts.bis;

import com.vgit.yunqiang.common.consts.ConstName;

public class OrderStateConsts {

    @ConstName("待审核")
    public static byte WAIT_SHIP_AUDITING = 0;

    @ConstName("待发货")
    public static byte WAIT_SHIP_SEND = 1;

    @ConstName("待收货")
    public static byte WAIT_SHIP_TAKE = 2;

    @ConstName("已完成")
    public static byte FINISHED = 3;

    @ConstName("取消申请")
    public static byte CANCEL_APPLY = 4;

    @ConstName("关闭")
    public static byte CLOSED = 5;

    @ConstName("删除")
    public static byte DELETED = 9;

}
