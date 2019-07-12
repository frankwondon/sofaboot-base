package com.api.app.intercept;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.api.app.util.RequestUtil;
import com.module.api.app.service.UserService;
import com.module.common.ResponseCode;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import com.module.common.constant.HeaderConstant;
import com.module.common.exception.AuthException;
import com.module.common.util.AppTokenUtil;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class AuthIntercept implements HandlerInterceptor {
    private UserService userService;

    public AuthIntercept(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String deviceId = request.getHeader(HeaderConstant.DEVICEID_NAME);
        String userAgent = request.getHeader(HeaderConstant.USERAGENT_NAME);
        String token = request.getHeader(HeaderConstant.TOKEN_NAME);
        String ip = RequestUtil.getIpAddress(request);
        log.info("ip:{},userAgent:{},deviceId:{},token:{}", ip, userAgent, deviceId, token);
        if (StrUtil.isBlank(token)){
            throw new AuthException(ResponseCode.C_302);
        }
        AppTokenUtil.verifier(token);
        AppTokenDto decode = AppTokenUtil.decode(token);
        //限制IP 需要考虑 移动端 频繁切换IP的情况
//        if (!StrUtil.equals(ip, decode.getIp())) {
//            throw new AuthException(ResponseCode.C_302);
//        }
        if (!StrUtil.equals(deviceId, decode.getSubject())) {
            throw new AuthException(ResponseCode.C_302);
        }
        if (!StrUtil.equals(userAgent, decode.getUserAgent())) {
            throw new AuthException(ResponseCode.C_302);
        }
        //需要验证登陆的接口
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AuthLogin annotationClass = method.getBeanType().getAnnotation(AuthLogin.class);
            if (annotationClass != null) {
                if (decode.getAppUserType() != AppUserType.USER) {
                    throw new AuthException(ResponseCode.C_302);
                }
                AppCurrentUser tokenUser = userService.getTokenUser(decode);
                if (tokenUser == null) {
                    throw new AuthException(ResponseCode.C_302);
                }
            } else {
                AuthLogin annotationMethod = method.getMethod().getAnnotation(AuthLogin.class);
                if (annotationMethod != null) {
                    if (decode.getAppUserType() != AppUserType.USER) {
                        throw new AuthException(ResponseCode.C_302);
                    }
                    AppCurrentUser tokenUser = userService.getTokenUser(decode);
                    if (tokenUser == null) {
                        throw new AuthException(ResponseCode.C_302);
                    }
                }
            }
        }
        return true;
    }
}
