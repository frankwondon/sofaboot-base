package com.module.api.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.api.app.service.*;
import com.module.api.app.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("module-api-app")
public class BeanConfigure {

    @Bean
    @SofaService
    public ProductService productService(){
        return new ProductServiceImpl();
    }

    @Bean
    @SofaService
    public UserService userService(){
        return new UserServiceImpl();
    }
    @Bean
    @SofaService
    public BannerListService bannerListService(){
        return new BannerListServiceImpl();
    }

    @Bean
    @SofaService
    public ShopProductService shopProductService(){
        return new ShopProductServiceImpl();
    }


    @Bean
    @SofaService
    public AddressService addressService(){
        return new AddressServiceImpl();
    }



    @Bean
    @SofaService
    public AppCartService appCartService(){
        return new AppCartServiceImpl();
    }
}
