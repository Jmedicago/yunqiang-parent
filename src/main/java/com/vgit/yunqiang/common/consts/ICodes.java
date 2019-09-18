package com.vgit.yunqiang.common.consts;

/**
 * 通用消息状态码常量
 */
public interface ICodes {

    @ConstName("成功")
    public static final int SUCCESS = 0;

    @ConstName("失败")
    public static final int FAILED = 1;

    @ConstName("需要登录")
    public static final int UNAUTHED = 2;

    @ConstName("非法访问")
    public static final int ILLEGAL_ACCESS = 3;

    @ConstName("没有操作权限，请联系系统管理员")
    public static final int NOT_AUTHORIZED = 4;

}
