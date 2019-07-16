package com.api.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthIntercept;
import com.api.app.intercept.RateLimiterIntercept;
import com.api.app.resolver.CurrentUserMethodArgumentResolver;
import com.module.api.app.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * MVC控制器
 * @author wangdong
 * @date 2019/5/21
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {
    private  String [] excludsPath=new String[]{
            "/doc.html"
            ,"/swagger-resources/**"
            ,"/webjars/**"
            ,"/v2/api-docs"
            ,"/v2/api-docs-ext"
            ,"/error"
            ,"/auth/visitorLogin"
    };

    @SofaReference
    private UserService userService;

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
                .excludePathPatterns(excludsPath);
        registry.addInterceptor(new RateLimiterIntercept())
                .excludePathPatterns(excludsPath);
    }
}
