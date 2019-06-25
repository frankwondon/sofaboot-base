package com.module.base.common.configure;


import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.base.common.service.SMSSendService;
import com.module.base.common.service.SMSSendServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigure {

    @SofaService
    @Bean
    public SMSSendService smsSendService(){
        return new SMSSendServiceImpl();
    }
}
