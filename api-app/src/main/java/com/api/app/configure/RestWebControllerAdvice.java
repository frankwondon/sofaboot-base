package com.api.app.configure;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.exception.*;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestWebControllerAdvice {
    @ExceptionHandler({JWTDecodeException.class, AuthException.class})
    public Response authentication(Exception exception){
        return Response.fail(ResponseCode.C_302);
    }

    @ExceptionHandler({DBException.class, LimitException.class})
    public Response dboperation(BaseException exception){
        return Response.fail(exception.getResponseCode());
    }


    @ExceptionHandler({ValidationException.class})
    public Response authentication(ValidationException exception){
        return Response.fail(exception.getResponseCode(),exception.getMsg());
    }

}
