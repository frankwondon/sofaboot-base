package com.api.app.intercept;

import cn.hutool.core.util.StrUtil;
import com.api.app.util.RequestUtil;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * 认证拦截器
 *
 * @author wangdong
 * @date 2019/6/25
 */

@Slf4j
public class AuthIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String deviceId = request.getHeader(HeaderConstant.DEVICEID_NAME);
        String userAgent = request.getHeader(HeaderConstant.USERAGENT_NAME);
        String token = request.getHeader(HeaderConstant.TOKEN_NAME);
        String ip = RequestUtil.getIpAddress(request);
        log.info("ip:{},userAgent:{},deviceId:{},token:{}",ip,userAgent,deviceId,token);
        AppTokenUtil.verifier(token);
        AppTokenDto decode = AppTokenUtil.decode(token);
        if (!StrUtil.equals(ip,decode.getIp())){
            throw new AuthException(ResponseCode.C_302);
        }
        if (!StrUtil.equals(deviceId,decode.getSubject())){
            throw new AuthException(ResponseCode.C_302);
        }
        if (!StrUtil.equals(userAgent,decode.getUserAgent())){
            throw new AuthException(ResponseCode.C_302);
        }
       //只拦截请求方法
        if (handler instanceof HandlerMethod) {

        }
        return true;
    }
}
