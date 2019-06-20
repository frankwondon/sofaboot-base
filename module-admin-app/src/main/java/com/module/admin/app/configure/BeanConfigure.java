package com.module.admin.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.admin.app.entity.AppProductType;
import com.module.admin.app.service.AppExpressTemplateService;
import com.module.admin.app.service.AppProductService;
import com.module.admin.app.service.AppProductTypeService;
import com.module.admin.app.service.impl.AppExpressTemplateServiceImpl;
import com.module.admin.app.service.impl.AppProductServiceImpl;
import com.module.admin.app.service.impl.AppProductTypeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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

}
