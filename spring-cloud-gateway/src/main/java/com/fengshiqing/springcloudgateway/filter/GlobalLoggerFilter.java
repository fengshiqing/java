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

import java.util.Date;

/**
 * 功能描述：全局过滤器：统计耗时。
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
        long beginTimestamp = new Date().getTime();
        log.info("【全局过滤器：统计耗时】【当前时刻：{}】", beginTimestamp);

        // 执行 业务逻辑
        Mono<Void> result = chain.filter(exchange);

        // 后置过滤
        long endTimestamp = new Date().getTime();
        log.info("【全局过滤器：统计耗时】【当前时刻：{} ; 本次执行时长：{} 毫秒】", endTimestamp, endTimestamp - beginTimestamp);

        return result;
    }

}
