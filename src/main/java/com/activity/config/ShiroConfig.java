package com.activity.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 高铭
 * @date 2019/9/12 - 13:25
 */
@Configuration
public class ShiroConfig {
    /**
     * shiro过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean factoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        //设置去添加页面以及删除页面都要登录验证，要是某个文件夹下面的资源都要配置拦截，
        // 那么拦截写法可以是 put("/user/**","authc");
        filterMap.put("/findBanDetailByAid","authc");
        filterMap.put("/AdminFindAllActivity","authc");
        filterMap.put("/AdminDeleteActiviy","authc");
        //设置去首页，登录页面不用登录验证
        filterMap.put("/toLoginUI","anon");
        filterMap.put("/adminLogin","anon");
        //修改调整的登录页面路径
        bean.setLoginUrl("/toLoginUI");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }
    /**
     * 安全管理器
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("adminRealm") AdminRealm adminRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
        return securityManager;
    }

    /**
     * 配置自定义的realm
     * @return
     */
    @Bean(name = "adminRealm")
    public AdminRealm adminRealm(){
        return new AdminRealm();
    }
    /**
     * 配置shiro，用于thymeleaf和shiro标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
