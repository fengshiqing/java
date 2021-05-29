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
        executor.setKeepAliveSeconds(180); // 允许的空闲时间，默认是60秒，超过次空闲时间，除了核心线程以外的空闲的线程会被销毁
        executor.setQueueCapacity(100); // 配置缓冲队列大小，最大线程都不够用时，先存在这个队列里等待
        executor.setThreadNamePrefix("SpringThreadPool_"); // 配置线程池中的线程的名称前缀
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务。CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();// 执行初始化

        LOGGER.info("【springThreadPool】【config】【end】");
        return executor;
    }
}

/*
 * 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 *
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maxPoolSize，建新的线程来处理被添加的任务。
 *
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maxPoolSize，那么通过handler所指定的策略来处理此任务。
 * 也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
 *
 * 当线程池中的线程数量大于corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 */
