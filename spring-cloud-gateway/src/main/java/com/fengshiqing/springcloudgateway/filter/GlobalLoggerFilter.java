/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 全局过滤器。
 */
@Component
public class GlobalLoggerFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 前置过滤
        Date begin = new Date();
        System.out.println("全局过滤器执行，当前时刻是：" + begin.getTime());

        // 执行后续逻辑
        Mono<Void> result = chain.filter(exchange);

        // 后置过滤
        Date end = new Date();
        System.out.println("全局过滤器执行，当前时刻是：" + end.getTime() + " ; 本次执行时长是 ： "
                + (end.getTime()-begin.getTime()) + "毫秒");

        return result;
    }
}
