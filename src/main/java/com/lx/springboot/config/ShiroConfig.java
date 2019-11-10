package com.lx.springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置拦截器
     *
     * 定义拦截URL权限，优先级从上到下
     * 1). anon : 匿名访问，无需登录
     * 2). authc : 登录后才能访问
     * 3). logout: 登出
     * 4). roles : 角色过滤器
     *
     * URL 匹配风格
     * 1). ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/；
     * 2). *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1；
     * 2). **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     *
     * 配置身份验证成功，失败的跳转路径
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/**/userInfo/**", "anon");// 获取用户匿名访问
        filterChainDefinitionMap.put("/static/**", "anon"); // 静态资源匿名访问
        filterChainDefinitionMap.put("/bootstrap/**", "anon"); // 静态资源匿名访问
        filterChainDefinitionMap.put("/css/**", "anon"); // 静态资源匿名访问
        filterChainDefinitionMap.put("/js/**", "anon"); // 静态资源匿名访问
        filterChainDefinitionMap.put("/img/**", "anon"); // 静态资源匿名访问
        filterChainDefinitionMap.put("/user/login", "anon");// 登录匿名访问
        filterChainDefinitionMap.put("/user/doLogin", "anon");// 登录匿名访问
        filterChainDefinitionMap.put("/user/logout", "logout"); // 用户退出，只需配置logout即可实现该功能
        filterChainDefinitionMap.put("/**", "authc"); // 其他路径均需要身份认证，一般位于最下面，优先级最低
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/user/login"); // 登录的路径
        shiroFilterFactoryBean.setSuccessUrl("/index"); // 登录成功后跳转的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/403"); // 验证失败后跳转的路径
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager 安全管理器；Shiro的核心
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     * 自定义Realm，可以多个
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 配置Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动创建代理类，若不添加，Shiro的注解可能不会生效。
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new
                DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 凭证匹配器
     *
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new
                HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * Shiro方言，支持Thymeleaf中使用shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
