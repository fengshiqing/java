/*
 * Copyright (c) 2020. fengshiqing 冯仕清
 */

package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolConfig.class);

    /**
     * 构造函数
     */
    public ThreadPoolConfig() {
        LOGGER.info("【初始化 线程池 配置】");
    }

    @Bean
    public Executor springThreadPool() {
        LOGGER.info("【springThreadPool】【start】");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5); //配置核心线程数

        //配置最大线程数
        executor.setMaxPoolSize(10);

        //配置队列大小
        executor.setQueueCapacity(100);

        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("spring-thread-pool-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();

        LOGGER.info("【springThreadPool】【end】");
        return executor;
    }
}
