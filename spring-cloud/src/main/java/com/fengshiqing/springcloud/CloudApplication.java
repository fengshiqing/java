/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.fengshiqing.springcloud.**.dao")
@EnableAsync  // 启用异步方法支持
@EnableDiscoveryClient
public class CloudApplication {

    /**
     * 全局 ApplicationContext 对象
     */
    public static ConfigurableApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(CloudApplication.class, args);

        String userName = appContext.getEnvironment().getProperty("user.name");
        String userAge = appContext.getEnvironment().getProperty("user.age");
        log.info("【userName：{}，userAge：{}】", userName, userAge);

        ConfigurableEnvironment environment = appContext.getEnvironment();

        // 遍历并打印所有的系统属性和环境变量
        for (String key : environment.getActiveProfiles()) {
            log.info("【getActiveProfiles】【遍历并打印所有的系统属性和环境变量】【key：{}，value：{}】", key, environment.getProperty(key));
        }
        // 遍历并打印所有的系统属性和环境变量
        for (String key : environment.getDefaultProfiles()) {
            log.info("【getDefaultProfiles】【遍历并打印所有的系统属性和环境变量】【key：{}，value：{}】", key, environment.getProperty(key));
        }

        ObjectMapper bean = appContext.getBean(ObjectMapper.class);
        System.out.println(bean);


        System.err.println("                ┏━━┓╻  ╻┏━━╸┏━━╸┏━━╸┏━━┓┏━━┓");
        System.err.println("                ┗━━┓┃  ┃┃   ┃   ┣━━╸┗━━┓┗━━┓");
        System.err.println("                ┗━━┛┗━━┛┗━━╸┗━━╸┗━━╸┗━━┛┗━━┛");
    }

}
