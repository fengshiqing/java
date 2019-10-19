package com.kunning.SpringBoot.activemq.producer;

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

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "【【【" + UUID.randomUUID().toString().substring(0, 6));
    }

    /**
     * 间隔3s定时投送消息
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "【【【Scheduled】" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("【定时发送消息成功】");
    }
}
