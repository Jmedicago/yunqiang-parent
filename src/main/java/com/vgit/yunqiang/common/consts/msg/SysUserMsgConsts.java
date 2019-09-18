package com.vgit.yunqiang.common.consts.msg;

import com.vgit.yunqiang.common.consts.ConstName;
import com.vgit.yunqiang.common.consts.ICodes;

public interface SysUserMsgConsts extends ICodes {

    public static final int BASE_CODE = 20000;

    @ConstName("手机号码已被注册")
    public static final int PHONE_NUMBER_EXISTS = 0 + BASE_CODE;

    @ConstName("图形验证码不正确")
    public static final int INVALIDATE_CAPTCHA = 1 + BASE_CODE;

    @ConstName("短信验证码不正确")
    public static final int INVALIDATE_SMS_CODE = 2 + BASE_CODE;

    @ConstName("短信验证码不存在或已过期")
    public static final int SMS_CODE_EXPIRED = 3 + BASE_CODE;

    @ConstName("未过重发时间（60s）")
    public static final int SMS_CODE_ERR_REQUEST = 4 + BASE_CODE;

    @ConstName("用户名或密码不正确")
    public static final int USER_PASS_INVALIDATE = 5 + BASE_CODE;

    @ConstName("登录信息已过期")
    public static final int TIME_OUT = 6 + BASE_CODE;

    @ConstName("手机号码不存在")
    public static final int PHONE_NUMBER_NON_EXISTS = 7 + BASE_CODE;

}
