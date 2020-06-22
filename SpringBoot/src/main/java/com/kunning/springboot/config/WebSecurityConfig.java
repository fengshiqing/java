package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableWebSecurity // 开启Spring Security
@EnableGlobalMethodSecurity(prePostEnabled =true) // 开启方法级别的安全控制。prePostEnabled =true会拦截@PreAuthorize修饰的方法
//@EnableGlobalMethodSecurity(prePostEnabled =true, securedEnabled =true, jsr250Enabled =true) // 开启方法级别的安全控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    /**
     * 构造函数
     */
    public WebSecurityConfig() {
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

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/index.html", "/static/**", "/loginPage", "/register");
//    }

    //password为BCryptPasswordEncoder加密123后的值


    /**
     * 功能描述：基于内存的方式，创建账户密码及其角色权限
     *
     * @param auth 权限
     *
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()) // 这种方式每次都要写重复代码
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("normal"); //normal表示一般通用的角色权限

        // 这种方式注册一个 passwordEncoder bean，直接拿来使用就行了，方便很多
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(this.passwordEncoder().encode("123456"))
                .roles("admin"); // admin表示管理员权限
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasAnyRole("admin","user")
//                .anyRequest() // 任何请求,登录后可以访问
//                .authenticated()//其他路径认证之后就可以访问
//                .and()
//                .formLogin() //  定义当需要用户登录时候，转到的登录页面。
//                .loginProcessingUrl("/doLogin")//处理登录请求的地址
//                .permitAll()
//                .and()
//                .csrf().disable();
//    }

}
