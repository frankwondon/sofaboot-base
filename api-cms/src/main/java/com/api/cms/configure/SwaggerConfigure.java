package com.api.cms.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfigure {
    @Bean(value = "cms")
    public Docket backAdmin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("网站")
                        .description("网站接口")
                        .version("1.0")
                        .build())
                .groupName("网站")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.cms.controller"))
                .paths(PathSelectors.any())
                .build();
    }







}
