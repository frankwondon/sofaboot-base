package com.api.admin.back.controller.shiro;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.service.user.BackUserService;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfigure {
    @SofaReference
    private BackUserService backUserService;


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        DefaultSubjectDAO defaultSubjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator=new DefaultSessionStorageEvaluator();
        //禁用session
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(defaultSubjectDAO);
        manager.setSubjectDAO(defaultSubjectDAO);
        manager.setRealm(new LoginRealm());
        return manager;
    }
}
