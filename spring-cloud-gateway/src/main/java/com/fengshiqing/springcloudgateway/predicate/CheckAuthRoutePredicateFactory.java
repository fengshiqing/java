/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.predicate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.springframework.http.server.PathContainer.parsePath;

/**
 * 功能描述：自定义断言
 */
@Slf4j
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }


    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                PathContainer path = parsePath(exchange.getRequest().getURI().getRawPath());

                return config.getName().equals("xushu");
            }

            @Override
            public Object getConfig() {
                return config;
            }

        };
    }


    /**
     * 用于接收配置文件中 断言的信息。
     */
    @Getter
    @Setter
    @Validated
    public static class Config {

        private String name;

    }

}
