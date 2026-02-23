/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration // 取代 application.xml配置文件
public class AppConfig {

    /**
     * 构造函数
     */
    public AppConfig() {
        log.info("【初始化 AppConfig 配置】");
    }

    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

}
