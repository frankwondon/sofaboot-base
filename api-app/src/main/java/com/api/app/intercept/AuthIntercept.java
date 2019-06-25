package com.api.app.intercept;

import com.module.common.constant.DefaultConstant;
import com.module.common.util.AppTokenUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 *
 * @author wangdong
 * @date 2019/6/25
 */

@Component
public class AuthIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
        //只拦截请求方法
//        if (handler instanceof HandlerMethod) {
//            String name = ((HandlerMethod) handler).getMethod().getName();
//            String token = request.getHeader(DefaultConstant.APP_TOKEN_NAME);
//            //验证token
//            AppTokenUtil.verifier(token);
//            return true;
//        }
//        return true;
    }
}
