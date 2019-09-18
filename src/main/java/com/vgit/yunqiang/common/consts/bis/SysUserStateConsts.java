package com.vgit.yunqiang.common.consts.bis;

import com.vgit.yunqiang.common.consts.ConstName;

public interface SysUserStateConsts {

    @ConstName("正常")
    public static byte NORMAL = 0;

    @ConstName("停用")
    public static byte DISABLED = 1;

    @ConstName("锁定")
    public static byte LOCKED = 2;

}
