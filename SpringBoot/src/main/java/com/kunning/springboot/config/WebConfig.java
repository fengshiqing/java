package com.kunning.springboot.config;

import com.kunning.springboot.interceptor.CommonIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述：扩展SpringMVC
 * SpringBoot2使用的Spring5，因此将WebMvcConfigurerAdapter改为 WebMvcConfigurer
 * 使用WebMvcConfigurer扩展SpringMVC好处既保留了SpringBoot的自动配置，又能用到我们自己的配置
 *
 * @author 冯仕清
 * @since 2020-06-22
 */
//@EnableWebMvc //如果我们需要全面接管SpringBoot中的SpringMVC配置则开启此注解，开启后，SpringMVC的自动配置将会失效。
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    CommonIntercepter commonIntercepter;

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //设置对“/”的请求映射到index
//        //如果没有数据返回到页面，没有必要用控制器方法对请求进行映射
//        registry.addViewController("/").setViewName("index");
//    }

    /**
     * 注册注册拦截器，这个是自定义的拦截器，使用了 Spring Security后，默认就提供了哟个拦截器，这个拦截器可以去掉了
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //SpringMVC下，拦截器的注册需要排除对静态资源的拦截(*.css,*.js)
        //SpringBoot已经做好了静态资源的映射，因此我们无需任何操作
        registry.addInterceptor(commonIntercepter)
                .addPathPatterns("/**") // 匹配符说明：/只会拦截路径，不会拦截页面；/*拦截所有的文件夹，不包含子文件夹；/**拦截所有的文件夹及其子文件夹
                .excludePathPatterns("/index.html", "/index.css", "/index.js", "/", "/user/login");
    }
}
