/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 功能描述：WebClient配置。
 *
 * @author 冯仕清
 * @since 2025-08-21
 */
@Slf4j
@Configuration
public class WebClientConfig {

    /**
     * 构造函数
     */
    public WebClientConfig() {
        log.info("【WebClientConfig】【初始化配置】");
    }


    /**
     * 功能描述：创建 WebClient 对象。
     * RestTemplate 是同步阻塞的，Spring后续会放弃。
     * WebClient 是异步非阻塞的，推荐使用 WebClient。
     * 具体用法，可以看这里：<a href="https://cloud.tencent.com/developer/article/1793360">Spring的WebClient基本使用</a>
     *
     * @return WebClient
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // 默认是 application/json ，其他场景需要自行设置
                .build();
    }

}