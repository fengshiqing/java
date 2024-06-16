/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    /**
     * 构造函数
     */
    public AppConfig() {
        LOGGER.info("【AppConfig】【初始化配置】");
    }

    /**
     * 功能描述：配置线程池。
     *
     * @return 线程池
     */
    @Bean
    public Executor commonThreadPool() {
        LOGGER.info("【init config】【Thread Pool】");
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
        executor.setThreadNamePrefix("commonTP--"); // 配置线程池中的线程的名称前缀
        // rejection-policy：当pool已经达到max size时，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();// 执行初始化
        return executor;
    }


    /**
     * 功能描述：配置 RestTemplate。这个不实用，可以删掉。
     *
     * @return RestTemplate
     */
    @Bean
    @Lazy // 没有这个注解的话，启动时会报错：RestTemplateBuilder找不到。原因是RestTemplateBuilder是懒加载的，所以这里也应该是懒加载。
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        LOGGER.info("【init config】【restTemplate】");
        return restTemplateBuilder.build();
    }


    /**
     * 功能描述：配置 redisTemplate。注意这个泛型。
     *
     * @param redisConnectionFactory 参数
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        LOGGER.info("【init config】【RedisTemplate<String, String>】");
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer); // 设置key和value的序列化规则
        redisTemplate.setValueSerializer(stringRedisSerializer); // 设置key和value的序列化规则
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }


    /**
     * 功能描述：配置 redisTemplate。注意这个泛型。上面哪个方法是我验证过的，没问题的，这个方法用的json解析的方法不一样，还么有验证过
     *
     * @param redisConnectionFactory 参数
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate11(RedisConnectionFactory redisConnectionFactory) {
        LOGGER.info("【init config】【RedisTemplate<String, Object>】");
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        return template;
    }

}
