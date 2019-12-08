package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：线程池初始化。
 * 自定义线程池的配置类，并在类上添加@EnableAsync 注解，然后在需要异步的方法上使用@Async("线程池名称") 该方法就可以异步执行了。
 *
 * @author 冯仕清
 * @since 2019/12/08 19:09
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorConfig.class);

    /**
     * 功能描述：配置异步线程池
     *
     * @return 线程池
     */
    @Bean
    public Executor asyncServiceExecutor() {
        LOGGER.info("【配置异步线程池】【start】");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 配置核心线程数
        executor.setMaxPoolSize(20); // 配置最大线程数
        executor.setKeepAliveSeconds(60); //
        executor.setQueueCapacity(999); // 配置队列大小
        executor.setThreadNamePrefix("async-pool-"); // 配置线程池中的线程的名称前缀

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize(); // 执行初始化

        LOGGER.info("【配置异步线程池】【end】");
        return executor;
    }

}
