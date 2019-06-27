package com.api.cms.configure;

import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.exception.DBException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestWebControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    public Response authentication(AuthenticationException exception){
        return Response.fail(ResponseCode.C_302,exception.getMessage());
    }

    @ExceptionHandler(DBException.class)
    public Response dboperation(DBException exception){
        return Response.fail(exception.getResponseCode());
    }
}
