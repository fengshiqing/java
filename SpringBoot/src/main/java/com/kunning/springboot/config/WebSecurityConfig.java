package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


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
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        userDetailsManager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return userDetailsManager;
    }

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
                .antMatchers("/hello/**") // 这个路径的资源必须通过认证
                .authenticated()//其他路径认证之后就可以访问
                .anyRequest().permitAll() // 其他的请求可以放行
                .and()
                .formLogin() // 允许表单登录
                .successForwardUrl("/hello/helloUser"); // 表单登录成功后跳转的页面地址
    }

}
