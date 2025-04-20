package com.fengshiqing.springcloudgateway.keyresolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("myKeyResolver")
public class MyKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String remoteAddr = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        return Mono.just(remoteAddr);
    }

}
