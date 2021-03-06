package com.api.back.resolver;

import com.module.common.bean.AdminCurrentUser;
import org.apache.shiro.SecurityUtils;
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
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if ( methodParameter.getParameterType().equals(AdminCurrentUser.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return  SecurityUtils.getSubject().getPrincipal();
    }
}
