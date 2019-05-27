package com.api.admin.back.shiro;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.service.BackUserService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfigure {
    @SofaReference
    private BackUserService backUserService;


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        Map<String, Filter> filterMap=new LinkedHashMap<>();
        filterMap.put("authc",new TokenFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        //静态文件地址
        filterChainDefinitionMap.put("/html/**", "anon");
        filterChainDefinitionMap.put("/resource/**", "anon");
        //swagger相关
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");

        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(new LoginRealm(backUserService));
        return securityManager;
    }






}
