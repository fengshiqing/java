package com.kunning.springboot.activemq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * 功能：生产者
 */
@Component
public class Queue_Producer {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue_Producer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "【【【" + UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * 间隔30s定时投送消息
     */
    @Scheduled(fixedDelay = 30000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "【【【Scheduled】" + UUID.randomUUID().toString().substring(0, 6));
        LOGGER.info("【定时发送消息成功】");
    }
}
