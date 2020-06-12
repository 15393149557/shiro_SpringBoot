package com.baizhi.zq.conf;

import com.baizhi.zq.realm.Realm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration       //将配置类教由spring工厂管理
public class ShiroFilterConf {

    //将shiro过滤器工厂对象交给spring工厂管理
    @Bean
    public ShiroFilterFactoryBean getUser(SecurityManager securityManager){
        //创建shiro过滤器工厂
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //将安全管理器交给shiro过滤器工厂
        factoryBean.setSecurityManager(securityManager);

        HashMap<String, String> map = new HashMap<>();

        /*
         *FormAuthenticationFilter authc 认证过滤器  只有认证成功的资源才能被访问
         *AnonymousFilter anon  匿名过滤器  不用认证都可以访问
         * */
        map.put("/**","authc");
        map.put("/test/login.jsp","anon");
        map.put("/user/login","anon");
        map.put("/main/main.jsp","anon");

        //定拦截策略  定义一个过滤器链
        factoryBean.setFilterChainDefinitionMap(map);

        //自定义拦截跳转的登录页面
        factoryBean.setLoginUrl("/test/login.jsp");
        return factoryBean;
    }

    //将安全管理器对象交给spring工厂管理
    @Bean
    public SecurityManager getSecurityManager(Realm myRealm,CacheManager cacheManager){
        //创建安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //将自定义Realm交给安全管理器
        securityManager.setRealm(myRealm);

        //配置缓存
        securityManager.setCacheManager(cacheManager);

        return securityManager;
    }


    //将自定义Realm对象交给spring工厂管理
    @Bean
    public Realm getMyRealm(HashedCredentialsMatcher credentialsMatcher){
        //配置自定义Realm
        Realm myRealm = new Realm();

        //将凭证匹配器给自定义Realm
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    //将凭证匹配器对象交给spring工厂管理
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){

        //创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5"); //设置加密算法
        credentialsMatcher.setHashIterations(1024);  //设置散列次数
        return credentialsMatcher;
    }

    //将缓存对象交给spring工厂管理
    @Bean
    public CacheManager getCacheManager(){

        //创建缓存
        CacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}
