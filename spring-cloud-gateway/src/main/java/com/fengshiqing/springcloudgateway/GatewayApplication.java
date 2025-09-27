/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
//        PathRoutePredicateFactory
//        GatewayPredicate

        ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayApplication.class, args);

        Map<String, Object> systemEnvironment = applicationContext.getEnvironment().getSystemEnvironment();
        // 遍历 systemEnvironment
        for (String key : systemEnvironment.keySet()) {
            System.err.println("【systemEnvironment】【遍历 systemEnvironment】【key：{}，value：{}】" + key + " ---- " + systemEnvironment.get(key));
        }


        String userId = applicationContext.getEnvironment().getProperty("biz.user.id");
        String userName = applicationContext.getEnvironment().getProperty("biz.user.name");
        String userAge = applicationContext.getEnvironment().getProperty("biz.user.age");
        System.err.println("【userId:】" + userId);
        System.err.println("【userName:】" + userName);
        System.err.println("【userAge:】" + userAge);


        System.err.println("                ┏━━┓╻  ╻┏━━╸┏━━╸┏━━╸┏━━┓┏━━┓");
        System.err.println("                ┗━━┓┃  ┃┃   ┃   ┣━━╸┗━━┓┗━━┓");
        System.err.println("                ┗━━┛┗━━┛┗━━╸┗━━╸┗━━╸┗━━┛┗━━┛");
    }

}
