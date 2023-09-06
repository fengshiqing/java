/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 功能描述：zookeeper配置。
 *
 * @author fengshiqing
 * @since 2021-12-19
 */
@Configuration
@PropertySource(value = { "classpath:conf/zookeeper.properties" })
public class ZooKeeperConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperConfig.class);

    /**
     * 构造器
     */
    public ZooKeeperConfig() {
        LOGGER.info("【初始化 zookeeper 配置】");
    }

    /**
     * zookeeper连接地址
     */
    @Value("${zookeeper.connect.url}")
    private String zookeeperUrl;

    /**
     * 重试次数
     */
    @Value("${zookeeper.retry.count}")
    private int retryCount;

    /**
     * 重试间隔时间，单位：毫秒
     */
    @Value("${zookeeper.retry.interval}")
    private int retryInterval;

    /**
     * session超时时间，单位：毫秒
     */
    @Value("${zookeeper.session.timeout}")
    private int sessionTimeout;

    /**
     * 连接超时时间，单位：毫秒
     */
    @Value("${zookeeper.connect.timeout}")
    private int connectTimeout;

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(retryInterval, retryCount);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperUrl, sessionTimeout, connectTimeout,
                retryPolicy);
        client.start();
        return client;
    }
}
