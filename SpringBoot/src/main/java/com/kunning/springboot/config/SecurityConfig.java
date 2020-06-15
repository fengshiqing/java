package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述：访问权限配置
 *
 * @author 冯仕清
 * @since 2020-06-16
 *
 * https://baijiahao.baidu.com/s?id=1668861945052132184&wfr=spider&for=pc
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true, securedEnabled =true, jsr250Enabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * 构造函数
     */
    public SecurityConfig() {
        LOGGER.info("【初始化 Spring Security 配置】");
    }

    /**
     * 自定义的加密算法
     *
     * @return 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/index.html", "/static/**", "/loginPage", "/register");
    }

    //password为BCryptPasswordEncoder加密123后的值
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("admin").password("$2a$10$2cPRItUHyE1GSZnrYWHiQevpbxn4ikWgOa1PYL5miWvqK8GFVCWb6").roles("admin")
                .and().withUser("java").password("$2a$10$rygGQylvmoAFmPcKQP6xvepNVAw9Bxp0sbAphxKQwhAV79Au0ECvq").roles("user");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasAnyRole("admin","user")
                .anyRequest() // 任何请求,登录后可以访问
                .authenticated()//其他路径认证之后就可以访问
                .and()
                .formLogin() //  定义当需要用户登录时候，转到的登录页面。
                .loginProcessingUrl("/doLogin")//处理登录请求的地址
                .permitAll()
                .and()
                .csrf().disable();
    }




}
