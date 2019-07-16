package com.api.app.intercept;

import cn.hutool.core.util.StrUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.module.common.ResponseCode;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.HeaderConstant;
import com.module.common.exception.AuthException;
import com.module.common.util.AppTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 请求速率拦截器不考虑多机的情况
 * @author wangdong
 * @date 2019/6/25
 */

@Slf4j
public class RateLimiterIntercept implements HandlerInterceptor {
    private final static Cache<String, com.google.common.util.concurrent.RateLimiter> limiterCache=
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            RateLimiter annotationClass = method.getMethodAnnotation(RateLimiter.class);
            if (annotationClass!=null){
                String token = request.getHeader(HeaderConstant.TOKEN_NAME);
                if (StrUtil.isBlank(token)){
                    throw new AuthException(ResponseCode.C_302);
                }
                AppTokenUtil.verifier(token);
                AppTokenDto decode = AppTokenUtil.decode(token);
                com.google.common.util.concurrent.RateLimiter limiter = limiterCache.getIfPresent(decode.getMobile());
                if (limiter==null){
                    limiter= com.google.common.util.concurrent.RateLimiter.create(annotationClass.limit());
                    limiterCache.put(decode.getMobile(),limiter);
                }
                if (!limiter.tryAcquire()){
                    throw new AuthException(ResponseCode.C_501);
                }
            }
        }
        return true;
    }

}
