package com.api.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthIntercept;
import com.api.app.resolver.CurrentUserMethodArgumentResolver;
import com.module.api.app.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author wangdong
 * @date 2019/5/21
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @SofaReference
    private UserService userService;
    /**
     * 添加参数注入
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserMethodArgumentResolver(userService));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthIntercept(userService))
                .excludePathPatterns("/swagger-ui.html"
                        ,"/swagger-resources/**"
                        ,"/webjars/**"
                        ,"/v2/api-docs"
                        ,"/error"
                        ,"/auth/visitorLogin"
                );
    }
}
