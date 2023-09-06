package com.kunning.web.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class Spring_JmsConsumer {

    /**
     * 自动注入 jmsTemplate
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * main方法
     */
    public static void main(String[] args) throws JMSException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring-mvc-mq.xml");// Spring-mvc.xml加载这个会报错，问题待解决

        Spring_JmsConsumer consumer = (Spring_JmsConsumer) ctx.getBean("spring_JmsConsumer");

//        producer.jmsTemplate.send(new MessageCreator() {
//
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage("【Spring整合ActiveMQ 之 发送消息到队列】");
//                return textMessage;
//            }
//        });
        // lambda表达式写法：
        TextMessage message = (TextMessage)consumer.jmsTemplate.receive();
        System.out.println("【收到消息：】" + message.getText());

        System.out.println("【收到消息：】" + (String)consumer.jmsTemplate.receiveAndConvert());

        System.out.println("【Spring整合ActiveMQ】【消息接收成功】");
    }

}
