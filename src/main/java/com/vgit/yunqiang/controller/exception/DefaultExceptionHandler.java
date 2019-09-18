package com.vgit.yunqiang.controller.exception;

import com.vgit.yunqiang.common.consts.ICodes;
import com.vgit.yunqiang.common.utils.Ret;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * 权限校验失败异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Ret processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        return Ret.me().setCode(ICodes.NOT_AUTHORIZED);
    }

}
