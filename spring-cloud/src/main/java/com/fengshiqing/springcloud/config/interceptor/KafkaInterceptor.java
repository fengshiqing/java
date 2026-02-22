/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.interceptor;

import com.fengshiqing.common.Constant.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * 功能描述：Kafka 发送消息时的拦截器。
 * kafka默认是没有配置任何拦截器的。这里我们为了业务逻辑，增加一个拦截器。
 *
 * @author 冯仕清
 * @since 2025-01-01
 */
@Slf4j
public class KafkaInterceptor implements ProducerInterceptor<String, String> {


    // 发送消息的前一刻触发：拦截消息，在消息头中加入 traceId
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecord) {
        String traceId = MDC.get(SysConstant.TRACE_ID);
        if (!StringUtils.hasText(traceId)) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        producerRecord.headers().add(SysConstant.TRACE_ID, traceId.getBytes());
        return producerRecord;
    }


    // 配置项，发送消息前触发
    @Override
    public void configure(Map<String, ?> map) {
        // 读取配置，并打印
        log.info("【KafkaInterceptor】【configure: {}】", map);
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            log.info("【KafkaInterceptor】【configure: {} = {}】", entry.getKey(), entry.getValue());
        }
    }


    // 发送消息后，并且收到了服务都的响应后，触发此代码执行。
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        log.info("【KafkaInterceptor】【onAcknowledgement: {}】", recordMetadata);
    }


    // 连接关闭时触发。
    @Override
    public void close() {
        // 清理资源
        log.info("【KafkaInterceptor】【close】");
    }

}
