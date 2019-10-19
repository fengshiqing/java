package com.kunning.SpringBoot.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * 功能：配置 ActiveMQ
 */
@Component
@EnableJms // 这个注解很重要，开启 JMS 的注解
public class ConfigBean {

    @Value("${myqueue}")
    private String myQueue;

    @Bean // <bean id="" class="">
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }
}
