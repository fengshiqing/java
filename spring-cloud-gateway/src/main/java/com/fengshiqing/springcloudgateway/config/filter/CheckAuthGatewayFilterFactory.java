/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.config.filter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 功能描述：自定义 网关的过滤器。
 */
@Slf4j
@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {

    public CheckAuthGatewayFilterFactory() {
        super(Config.class);
    }


    // 如果可以简化配置方案。当前方法返回简化配置参数命名列表
    // RequestPath=nameValue,pathValue
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }


    // 过滤逻辑
    @Override
    public GatewayFilter apply(Config config) {
        // 过滤方法
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();
            log.info("【CheckAuthGatewayFilterFactory】【本次请求地址是：{} ; 配置的参数是：name={}】", path, config.getName());

            String name = exchange.getRequest().getQueryParams().getFirst("name");
            if (name != null) {
                if (name.equals(config.getName())) {
                    return chain.filter(exchange);
                } else {
                    // 返回 404 然后结束
                    exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        };

    }


    // 当前过滤器需要使用的配置内容
    @Getter
    public static class Config {

        private String name;

        public Config setName(String name) {
            this.name = name;
            return this;
        }

    }

}
