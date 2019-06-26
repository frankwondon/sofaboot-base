package com.api.app.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfigure {
    @Bean(value = "cms")
    public Docket backAdmin() {
        //构建公共参数
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        Parameter token = ticketPar.name("x-token").description("token值")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(true).build();
        Parameter versionName = ticketPar.name("version-name").description("版本号")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(true).build();
        Parameter versionCode = ticketPar.name("version-code").description("版本名称")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(true).build();
        pars.add(token);
        pars.add(versionName);
        pars.add(versionCode);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("APP接口")
                        .description("APP接口")
                        .version("1.0")
                        .build())
                .groupName("APP接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.app.controller"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }







}
