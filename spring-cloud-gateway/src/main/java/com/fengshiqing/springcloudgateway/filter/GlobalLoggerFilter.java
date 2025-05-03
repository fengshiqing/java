/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 功能描述：全局过滤器：统计耗时。
 * 全局过滤器：拦截所有请求，无需配置就是针对所有的请求。
 * 局部过滤器：需要在路由中配置后才生效。
 *
 * Reactor Netty 访问日志，请设置：-Dreactor.netty.http.server.accessLogEnabled=true 这个配置必须是java系统属性，而不是spring-boot属性。
 *
 *
 * @author fengshiqing
 * @since 2025-01-01
 */
@Slf4j
@Component
public class GlobalLoggerFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 前置过滤
        long start = System.currentTimeMillis();
        log.info("【全局过滤器】【当前时刻：{}】", start);

        String path = exchange.getRequest().getPath().value();
        log.info("【全局过滤器】【path：{}】", path);

        // 执行 业务逻辑
        Mono<Void> result = chain.filter(exchange);

        // 后置过滤
        long end = System.currentTimeMillis();
        log.info("【全局过滤器】【当前时刻：{}】【本次执行时长：{} 毫秒】", end, end - start);

        return result;
    }

}
