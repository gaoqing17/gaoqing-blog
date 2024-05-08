package com.gaoqing.gaoqingblog.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    //ShiroFilterFactoryBean设置安全管理器
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("defaultManager") DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        Map<String, String> filterMap = new LinkedHashMap<>();
        //注入拦截器
        //使用通配符拦截请求
        filterMap.put("/admin/*", "authc");
        filterMap.put("/admin/login","anon");
        filterMap.put("/error", "authc");
        System.out.println("执行了=>授权设置登录的请求");
        //设置登录的请求
        bean.setLoginUrl("/admin/login");
        //设置权限

        bean.setFilterChainDefinitionMap(filterMap);
        bean.setSecurityManager(defaultSecurityManager);
        return bean;

    }
    //2.DefaultWebSecurityManager
    @Bean(value = "defaultManager")
    public DefaultWebSecurityManager DefaultWebSecurityManager(@Qualifier("userRealm") userRealm userRealm){
        DefaultWebSecurityManager defaultManager = new DefaultWebSecurityManager();
        defaultManager.setRealm(userRealm);
        return defaultManager;
    }

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1);
        return credentialsMatcher;
    }

    //1.创建Realm对象，需要自定义
    @Bean
    public userRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        userRealm userRealm = new userRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }
}
