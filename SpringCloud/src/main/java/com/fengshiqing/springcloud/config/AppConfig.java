/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class AppConfig {

    /**
     * 构造函数
     */
    public AppConfig() {
        log.info("【AppConfig】【初始化配置】");
    }


    /**
     * 功能描述：记录请求日志。
     * Spring Boot有一个内置的日志记录解决方案，比自己写切面要优雅的多。
     * <p>
     * 1、通过 AbstractRequestLoggingFilter 可以记录请求的详细信息。
     * AbstractRequestLoggingFilter 有两个不同的实现类，我们常用的是 CommonsRequestLoggingFilter。
     * 通过 CommonsRequestLoggingFilter 开发者可以自定义记录请求的参数、请求体、请求头和客户端信息。
     * 启用方式很简单，加个如下的配置就行了：
     * <p>
     * 2、然后在 application.properties 中配置日志级别为 DEBUG，就可以详细记录请求信息：
     * logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG
     *
     * @return CommonsRequestLoggingFilter
     */
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter reqLogFilter = new CommonsRequestLoggingFilter();
        reqLogFilter.setIncludeQueryString(true);
        reqLogFilter.setIncludeClientInfo(true);
        reqLogFilter.setIncludeHeaders(true);
        reqLogFilter.setIncludePayload(true);
        reqLogFilter.setMaxPayloadLength(1024); // 默认是 50 个字符，很多参数打印不全的。
        reqLogFilter.setBeforeMessagePrefix("【请求前】【打印日志】【开始】");
        reqLogFilter.setBeforeMessageSuffix("【请求前】【打印日志】【结束】");
        reqLogFilter.setAfterMessagePrefix("【请求后】【打印日志】【开始】");
        reqLogFilter.setAfterMessageSuffix("【请求后】【打印日志】【结束】");
        return reqLogFilter;
    }


    /**
     * 功能描述：配置线程池。
     *
     * @return 线程池
     */
    @Bean
    public Executor commonThreadPool() {
        log.info("【init config】【Thread Pool】");
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
         * 池满时的拒绝策略
         * AbortPolicy(默认)：抛异常
         * DiscardPolicy/DiscardOldestPolicy：放弃该线程
         * CallerRunsPolicy：通知该线程的创建者，让其不要提交新的线程。这句话的意思就是不启动线程的start() 方法吧？
         */
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 核心线程数，线程池维护的最少线程数量
        executor.setMaxPoolSize(100); // 最大线程数
        executor.setKeepAliveSeconds(300); // 允许的空闲时间，默认是60秒，超过次空闲时间，除了核心线程以外的空闲的线程会被销毁
        executor.setQueueCapacity(999); // 配置缓冲队列大小，最大线程都不够用时，先存在这个队列里等待
        executor.setThreadNamePrefix("common-Async-TP--"); // 配置线程池中的线程的名称前缀
        // rejection-policy：当pool已经达到max size时，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();// 执行初始化
        return executor;
    }


    @Bean
    @LoadBalanced // @LoadBalanced是服务发现和负载均衡的一个标识标签不可以省略
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
