/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.config;

import com.fengshiqing.springcloudgateway.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalWebMvcConfig implements WebMvcConfigurer {

    /**
     * 重写父类提供的跨域请求处理的接口
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        // 添加映射路径
//        registry.addMapping("/**")
//                // 放行哪些原始域
//                .allowedOrigins("*")
//                // 是否发送Cookie信息
//                .allowCredentials(true)
//                // 放行哪些原始域(请求方式)
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD")
//                // 放行哪些原始域(头部信息)
//                .allowedHeaders("*")
//                // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//                .exposedHeaders("Server","Content-Length", "Authorization", "Access-Token", "Access-Control-Allow-Origin","Access-Control-Allow-Credentials");
//    }

    /**
     * 功能描述：添加权限拦截器
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**/*") // 禁止所有的非登陆页面
                .excludePathPatterns("/user/login"); // 放行登陆页面
    }


}
