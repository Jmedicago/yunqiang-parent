package com.vgit.yunqiang.common.exception;

import com.vgit.yunqiang.common.exception.base.BaseException;

public class UserBlockedException extends UserException {

    public UserBlockedException(String reason) {
        super("user.blocked", new Object[]{reason});
    }

}
