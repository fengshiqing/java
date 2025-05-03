/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 限流 & 熔断 的配置。
 *
 * @author fengshiqing
 * @since 2025-05-03
 */
//@Configuration
public class SentinelConfig {

    /**
     * 默认的 默认的DefaultBlockRequestHandler 有bug
     * <a href="https://github.com/alibaba/Sentinel/issues/3298">...</a>
     */
    public SentinelConfig() {
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable ex) {
                return ServerResponse.status(429).body(Mono.just("限流啦，请求太频繁"), String.class);
            }
        });
    }


    /**
     * 上面的构造函数，可以使用如下的方式替代：自定义 熔断处理
     */
//    @PostConstruct
    public void init() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable ex) {
                // 自定义 熔断处理
                return ServerResponse.status(429)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just("熔断啦，请求太频繁"), String.class);
//                return ServerResponse.status(429)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(BodyInserters.fromValue("降级了"));
            }
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
