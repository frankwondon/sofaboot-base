package com.module.base.common.configure;


import com.module.base.common.util.HandlerSmsSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 *由spring容器管理的bean
 *@author wangdong
 *@date 2019/6/27
 */
@Configuration
public class SpringBeanConfigure {

    @Bean
    public HandlerSmsSender handlerSmsSender(){
        return new HandlerSmsSender();
    }


}
