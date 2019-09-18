package com.vgit.yunqiang.common.exception;

public class UserPasswordRetryLimitExceedException extends UserException {

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }

}
