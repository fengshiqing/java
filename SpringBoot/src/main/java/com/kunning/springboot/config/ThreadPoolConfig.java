/*
 * Copyright (c) 2020. fengshiqing 冯仕清
 */

package com.kunning.springboot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 功能描述：配置 Spring线程池。
 *
 * @author fengshiqing
 * @since 2019-02-08
 */
@Configuration
public class ThreadPoolConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolConfig.class);

    /**
     * 构造函数
     */
    public ThreadPoolConfig() {
        LOGGER.info("【初始化 Spring线程池 配置】");
    }

    @Bean
    public Executor springThreadPool() {
        LOGGER.info("【springThreadPool】【config】【start】");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 核心线程数，线程池维护的最少线程数量
        executor.setMaxPoolSize(100); // 配置最大线程数
        executor.setQueueCapacity(100); // 配置缓冲队列大小，最大线程都不够用时，先存在这个队列里等待
        executor.setThreadNamePrefix("SpringThreadPool_"); // 配置线程池中的线程的名称前缀
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务。CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();// 执行初始化

        LOGGER.info("【springThreadPool】【config】【end】");
        return executor;
    }
}
