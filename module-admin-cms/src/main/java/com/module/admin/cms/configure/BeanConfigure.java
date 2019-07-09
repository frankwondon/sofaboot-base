package com.module.admin.cms.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.service.CmsBannerService;
import com.module.admin.cms.service.CmsNewsService;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.admin.cms.service.impl.CmsBannerServiceImpl;
import com.module.admin.cms.service.impl.CmsNewsServiceImpl;
import com.module.admin.cms.service.impl.CmsProductServiceImpl;
import com.module.admin.cms.service.impl.CmsProductTypeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "module-admin-cms")
public class BeanConfigure {


    @Bean
    @SofaService
    public CmsBannerService bannerService(){
        return new CmsBannerServiceImpl();
    }

    @Bean
    @SofaService
    public CmsProductService productService(){
        return new CmsProductServiceImpl();
    }

    @Bean
    @SofaService
    public CmsProductTypeService productTypeService(){
        return new CmsProductTypeServiceImpl();
    }


    @Bean
    @SofaService
    public CmsNewsService newsService(){
        return new CmsNewsServiceImpl();
    }

}
