package com.lc.shiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    创建ShiroFilterFactoryBean
    @Bean
public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){

    ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

//    shiro内置过滤器
    /**  常用过滤器：
     *      anon:无需认证即可访问
     *      authc:要授权才可访问
     *      user:如果使用rememberMe的功能可以直接访问
     *      perms:该资源必须得到资源授权才可以访问
     *      roles:该资源必须得到角色授权才可以访问
     */
    Map<String,String> filterMap=new LinkedHashMap<>();
//    filterMap.put("/add","authc");
//    filterMap.put("/update","authc");

//  所有路径都被拦截：filterMap.put("/**","authc");
//一些路径不拦截（需要被放过去）
    filterMap.put("/th","anon");
    filterMap.put("/login","anon");

//    授权过滤器
    filterMap.put("/add","perms[user:add]");
    filterMap.put("/update","perms[user:update]");

//    配置自定义login.html
    shiroFilterFactoryBean.setLoginUrl("/login");
//    配置未授权页面
    shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
    return shiroFilterFactoryBean;
}

//      创建DefaultWebSecurityManager
    @Bean("securityManager")
public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
    DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
    securityManager.setRealm(userRealm);
    return securityManager;
}

//    创建realm
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


//    配置ShiroDialect:用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
