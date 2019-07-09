package com.module.admin.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.admin.app.entity.AppProductType;
import com.module.admin.app.service.*;
import com.module.admin.app.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "module-admin-app")
public class BeanConfigure {

    @Bean
    @SofaService
    public AppProductService appProductService(){
        return new AppProductServiceImpl();
    }

    @Bean
    @SofaService
    public AppProductTypeService appProductTypeService(){
        return new AppProductTypeServiceImpl();
    }

    @Bean
    @SofaService
    public AppExpressTemplateService appExpressTemplateService(){
        return new AppExpressTemplateServiceImpl();
    }

    @Bean
    @SofaService
    public AppBannerService appBannerService(){
        return new AppBannerServiceImpl();
    }

    @Bean
    @SofaService
    public AppRecommendService appRecommendService(){
        return new AppRecommendServiceImpl();
    }

    @Bean
    @SofaService
    public AppUserService appUserService(){
        return  new AppUserServiceImpl();
    }

    @Bean
    @SofaService
    public AppOrderService appOrderService(){return new AppOrderServiceImpl();}
}
