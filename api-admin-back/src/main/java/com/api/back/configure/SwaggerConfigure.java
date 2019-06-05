package com.api.back.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfigure {
    @Bean(value = "backAdmin")
    public Docket backAdmin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("后台管理")
                        .description("后台管理")
                        .version("1.0")
                        .build())
                .groupName("后台管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.back.controller.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "cmsAdmin")
    @Order(value = 1)
    public Docket cmsAdmin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo( new ApiInfoBuilder()
                        .title("网站管理")
                        .description("网站管理")
                        .version("1.0")
                        .build())
                .groupName("网站管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.back.controller.cms"))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean(value = "common")
    @Order(value = 1)
    public Docket common() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo( new ApiInfoBuilder()
                        .title("公共接口")
                        .description("公共接口")
                        .version("1.0")
                        .build())
                .groupName("公共接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.back.controller.common"))
                .paths(PathSelectors.any())
                .build();
    }





}
