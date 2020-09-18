/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * 功能描述：配置 ActiveMQ
 */
@Configuration
@EnableJms // 这个注解很重要，开启 JMS 的注解
public class ActiveMqConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMqConfig.class);

    /**
     * 构造函数
     */
    public ActiveMqConfig() {
        LOGGER.info("【初始化 ActiveMq 配置】");
    }

    /**
     * 功能描述：新建一个queue队列。
     *
     * @return Queue队列
     */
    @Bean // <bean id="" class="">
    public Queue queue() {
        LOGGER.info("【配置 ActiveMQ queue 队列】");
        return new ActiveMQQueue("SpringBoot-queue001");
    }

}
