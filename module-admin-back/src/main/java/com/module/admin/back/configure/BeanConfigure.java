package com.module.admin.back.configure;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.admin.back.service.BackMenuService;
import com.module.admin.back.service.BackRoleService;
import com.module.admin.back.service.BackUserService;
import com.module.admin.back.service.impl.BackMenuServiceImpl;
import com.module.admin.back.service.impl.BackRoleServiceImpl;
import com.module.admin.back.service.impl.BackUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "module-admin-back")
public class BeanConfigure {


    @Bean
    @SofaService
    public BackUserService backUserService(){
        return new BackUserServiceImpl();
    }

    @Bean
    @SofaService
    public BackRoleService backRoleService(){
        return new BackRoleServiceImpl();
    }

    @Bean
    @SofaService
    public BackMenuService backMenuService(){
        return new BackMenuServiceImpl();
    }

}
