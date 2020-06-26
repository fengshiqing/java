package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 功能描述：安全配置/访问权限配置。
 * 三要素：
 * 1、定义用户信息服务（查询用户信息）
 * 2、密码加密器
 * 3、安全拦截机制（最重要）
 *
 * @author 冯仕清
 * @since 2020-06-16
 *
 * https://baijiahao.baidu.com/s?id=1668861945052132184&wfr=spider&for=pc
 */
@Configuration
@EnableWebSecurity // 开启Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别的安全控制。prePostEnabled =true会拦截@PreAuthorize修饰的方法
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
     * 功能描述：定义用户信息服务（查询用户信息）
     *
     * @return userDetailsManager
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        userDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return userDetailsManager;
//    }

    /**
     * 功能描述：密码加密器
     *
     * @return 加密器对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance(); // 暂时用明文进行密码验证
    }

    /**
     * 功能描述：安全拦截机制
     *
     * @param http HttpSecurity对象
     *
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/admin/**").hasRole("admin") // 这个路径的资源必须通过认证
//                .antMatchers("/user/**").hasAnyRole("admin", "user") // 这个路径的资源必须通过认证
//                .anyRequest() // 任何请求,登录后可以访问
//                .authenticated()//其他路径认证之后就可以访问
//                .and()
//                .formLogin() //  定义当需要用户登录时候，转到的登录页面。
//                .loginProcessingUrl("/doLogin")//处理登录请求的地址
//                .permitAll()
//                .and()
//                .csrf().disable();
        http.authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/") // 这个路径的资源必须通过认证
                .authenticated()//其他路径认证之后就可以访问
                .anyRequest().permitAll() // 其他的请求可以放行
                .and()
                .formLogin() // 允许表单登录
                .successForwardUrl("/hello/hello"); // 表单登录成功后跳转的页面地址
    }

}
