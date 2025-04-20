/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class RequestPathGatewayFilterFactory
        extends AbstractGatewayFilterFactory<RequestPathGatewayFilterFactory.Config> {
    public RequestPathGatewayFilterFactory(){
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
                System.out.println("路由过滤器：本次请求地址是="+path
                        +"; 配置的参数是：[name="+config.getName()
                        +",path="+config.getPath()+"]");
                return chain.filter(exchange);
            }
        };
    }

    // 当前过滤器需要使用的配置内容
    public static class Config{
        private String name;
        private String path;

        public String getName() {
            return name;
        }

        public Config setName(String name) {
            this.name = name;
            return this;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
