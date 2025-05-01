/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
//        PathRoutePredicateFactory
//        GatewayPredicate

        ConfigurableApplicationContext applicationContext = SpringApplication.run(GatewayApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("【user name :】" + userName);
        System.err.println("【user age : 】" + userAge);
    }

}
