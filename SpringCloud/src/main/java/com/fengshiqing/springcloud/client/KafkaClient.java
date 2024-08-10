/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.client;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 功能描述：kafka生产者
 *
 * @author fengshiqing
 * @since 2024-03-30
 */
@AllArgsConstructor
@Slf4j
@Component
public class KafkaClient {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * topic名称
     */
    private static final String TOPIC_NAME = "springboot.topic.fengshiqing";

    /**
     * 功能描述：测试发送消息。
     *
     * @param obj 消息内容
     */
    public void sendTestMsg(String obj) {
        log.info("【准备发送消息为：{}】", obj);

        //发送消息
        ListenableFuture<SendResult<String, Object>> future = (ListenableFuture<SendResult<String, Object>>) kafkaTemplate.send(TOPIC_NAME, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("【生产者 发送消息成功】" + TOPIC_NAME + "【消息内容：{}】", stringObjectSendResult.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info("【生产者 发送消息成功】" + TOPIC_NAME + "【happened Exception】", throwable);
            }
        });
    }

    /**
     * 功能描述：测试发送消息。
     *
     * @param obj 消息内容
     */
    public void sendTestMsg(String obj, String msgKey) {
        log.info("【准备发送消息为：{}，msgKey: {}】", obj, msgKey);

        //发送消息
        ListenableFuture<SendResult<String, Object>> future = (ListenableFuture<SendResult<String, Object>>) kafkaTemplate.send(TOPIC_NAME, msgKey, obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("【生产者 发送消息成功】" + TOPIC_NAME + "【消息内容：{}】", stringObjectSendResult.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info("【生产者 发送消息成功】" + TOPIC_NAME + "【happened Exception】", throwable);
            }
        });
    }


    /**
     * 下面的主题是一个数组，可以同时订阅多主题，只需按数组格式即可，也就是用","隔开
     */
    @KafkaListener(topics = {TOPIC_NAME})
    public void receiveTestMsg(ConsumerRecord<?, ?> record){
        log.info("【消费者收到的消息key: {}】", record.key());
        log.info("【消费者收到的消息value: {}】", record.value().toString());
    }

}
