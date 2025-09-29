/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.config.filter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 功能描述：自定义 网关的过滤器。
 */
@Slf4j
@Component
public class RequestPathGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestPathGatewayFilterFactory.Config> {

    public RequestPathGatewayFilterFactory() {
        super(Config.class);
    }


    // 如果可以简化配置方案。当前方法返回简化配置参数命名列表
    // RequestPath=nameValue,pathValue
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "path");
    }


    // 过滤逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            // 过滤方法
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String path = exchange.getRequest().getPath().toString();
                log.info("【RequestPathGatewayFilterFactory】【本次请求地址是={}; 配置的参数是：name={}, path={}】", path, config.getName(), config.getPath());
                return chain.filter(exchange);
            }
        };
    }


    // 当前过滤器需要使用的配置内容
    @Getter
    public static class Config {

        private String name;

        private String path;

        public Config setName(String name) {
            this.name = name;
            return this;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

}
