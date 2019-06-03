package com.api.cms.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 *
 * @author wangdong
 * @date 2019/5/21
 */
@Configuration
public class WebConfigure implements WebMvcConfigurer {

    /**
     * 添加参数注入
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
