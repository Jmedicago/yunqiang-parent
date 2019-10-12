package com.vgit.yunqiang.controller.utils;

import com.vgit.yunqiang.pojo.SysUser;
import org.apache.shiro.SecurityUtils;

public class UserContext {

    public static SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getUserId() {
        return UserContext.getUser().getId();
    }

}
