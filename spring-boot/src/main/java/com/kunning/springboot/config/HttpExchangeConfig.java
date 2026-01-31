/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.kunning.springboot.config;

import com.kunning.springboot.constant.SysConstant;
import com.kunning.springboot.service.client.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.nio.charset.StandardCharsets;

/**
 * 功能描述：配置 @HttpExchange 声明的客户端。
 * Springboot默认不自动扫描@HttpExchange注解，需要手动注册Bean
 *
 * @author 冯仕清
 * @since 2025-12-21
 */
@Slf4j
@Configuration
public class HttpExchangeConfig {

    public HttpExchangeConfig() {
        log.info("【初始化 HttpExchangeConfig 配置】");
    }

    // =================================================================================================================

    @Bean
    public ProductClient productClient(RestClient.Builder builder) {

        // 1、配置超时时间
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(); // 使用 JdkClientHttpRequestFactory 作为底层 HTTP 客户端工厂
        requestFactory.setReadTimeout(30000); // 设置读取超时为 30 秒

        RestClient restClient = builder
                .baseUrl("www.fengshiqing.com")
                .requestFactory(requestFactory)
                // 2、拦截器：可以执行 增加traceId、增加鉴权参数 等操作
                .requestInterceptor((request, body, execution) -> {
                    request.getHeaders().add(SysConstant.TRACE_ID, MDC.get(SysConstant.TRACE_ID));
                    ClientHttpResponse execute = execution.execute(request, body);
                    // 这里也可以 请求头 打印/保存 日志。解析响应，不过需要缓存响应，要不然业务中就读不到了
                    log.info("【商品服务客户端】【uri：{}，请求头：{}】【请求体：{}】", request.getURI(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
                    return execute;
                })
                .build();

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build()
                .createClient(ProductClient.class);
    }

}
