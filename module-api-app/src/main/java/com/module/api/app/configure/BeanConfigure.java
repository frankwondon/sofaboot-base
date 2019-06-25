package com.module.api.app.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.api.app.service.ProductService;
import com.module.api.app.service.UserService;
import com.module.api.app.service.impl.ProductServiceImpl;
import com.module.api.app.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
}
