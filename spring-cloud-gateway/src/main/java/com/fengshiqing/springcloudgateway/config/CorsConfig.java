/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 功能描述：CORS跨域配置。
 * 可以使用yml文件来配置。
 *
 * @author fengshiqing
 * @since 2025-05-03
 */
@Slf4j
@Configuration
public class CorsConfig {


    /**
     * 构造函数
     */
    public CorsConfig() {
        log.info("【初始化 CorsConfig 配置】");
    }


    /**
     * 配置跨域
     *
     * @return CorsWebFilter：专为 WebFlux 设计的 CORS 过滤器。   对应传统 Spring MVC 中的 CorsFilter
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 配置跨域规则
//        config.setAllowCredentials(true);  // 允许携带凭证(cookie等)，为true时，浏览器会携带cookie，此时不允许 addAllowedOrigin 为 *
         config.addAllowedOrigin("https://trusted-domain.com"); // 指定允许的域名，可多次调用添加多个允许的源，多个源时，不能有 * ，否则会抛出异常
        // config.addAllowedOrigin("http://localhost:8080");     // 开发环境允许
        config.addAllowedOrigin("*"); // 允许所有来源
        config.addAllowedMethod("*"); // 允许所有HTTP方法
        config.addAllowedHeader("*"); // 允许所有请求头

        // 注册CORS配置到所有路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser()); // PathPatternParser：WebFlux 使用的路径匹配解析器。   替代传统 AntPathMatcher
        source.registerCorsConfiguration("/**", config);  // 应用到所有路径

        return new CorsWebFilter(source);
    }
}