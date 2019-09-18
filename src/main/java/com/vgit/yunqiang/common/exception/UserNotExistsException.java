package com.vgit.yunqiang.common.exception;

import com.vgit.yunqiang.common.exception.base.BaseException;

public class UserNotExistsException extends UserException {

    public UserNotExistsException() {
        super("user.not.exists", null);
    }

}
