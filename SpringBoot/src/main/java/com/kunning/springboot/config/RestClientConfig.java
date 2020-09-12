/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述：配置Spring的 RestTemplate
 *
 * @author 冯仕清
 * @since 2020/09/12 21:56
 */
@Configuration
public class RestClientConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * Sring 提供的 RestTemplateBuilder
     */
    final RestTemplateBuilder restTemplateBuilder;

    /**
     * 构造函数
     */
    public RestClientConfig(RestTemplateBuilder restTemplateBuilder) {
        LOGGER.info("【初始化 Spring 的 RestTemplate】");
        this.restTemplateBuilder = restTemplateBuilder;
    }


    @Bean
    public RestTemplate springRestTemplate() { // restTemplate名字被servicecomb占用了，因此换个名字
        return restTemplateBuilder.build();
    }

}
