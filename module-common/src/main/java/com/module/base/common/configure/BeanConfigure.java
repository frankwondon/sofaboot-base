package com.module.base.common.configure;


import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.base.common.service.SMSSendService;
import com.module.base.common.service.impl.SMSSendServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *由sofa管理的bean
 *@author wangdong
 *@date 2019/6/27
 */
@Configuration
public class BeanConfigure {

    @Bean
    public SMSSendService smsSendService(){
        return new SMSSendServiceImpl();
    }


}
