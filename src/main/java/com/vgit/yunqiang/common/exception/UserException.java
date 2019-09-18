package com.vgit.yunqiang.common.exception;

import com.vgit.yunqiang.common.exception.base.BaseException;

public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
