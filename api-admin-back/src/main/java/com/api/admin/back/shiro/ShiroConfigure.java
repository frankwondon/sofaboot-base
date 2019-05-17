package com.api.admin.back.shiro;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.service.user.BackUserService;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfigure {
    @SofaReference
    private BackUserService backUserService;


    //权限管理，配置主要是Realm的管理认证
    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(new LoginRealm(backUserService));
        return securityManager;
    }

    public void filter(){

        BasicHttpAuthenticationFilter filter=new BasicHttpAuthenticationFilter();
    }
//    //Filter工厂，设置对应的过滤条件和跳转条件
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        Map<String,String> map = new HashMap<String, String>();
//        //登出
//
//        //登录
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        //首页
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //错误页面，认证不通过跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//        return shiroFilterFactoryBean;
//    }

}
