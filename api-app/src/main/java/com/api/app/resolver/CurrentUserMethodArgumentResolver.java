package com.api.app.resolver;

import com.module.api.app.service.UserService;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.HeaderConstant;
import com.module.common.util.AppTokenUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自动注入参数
 *@author wangdong
 *@date 2019/5/20
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private UserService userService;

    public CurrentUserMethodArgumentResolver(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if ( methodParameter.getParameterType().equals(AppCurrentUser.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader(HeaderConstant.TOKEN_NAME);
        AppTokenDto decode = AppTokenUtil.decode(token);
        AppCurrentUser tokenUser = userService.getTokenUser(decode);
        return tokenUser;
    }
}
