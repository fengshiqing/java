package com.kunning.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MapperScan("com.kunning.springboot.dao")
public class MyBatisConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfig.class);

    public MyBatisConfig() {
        LOGGER.info("【初始化 Mybatis 配置】");
    }
}
