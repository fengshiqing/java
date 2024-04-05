package com.kunning.web.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsConsumer {

    /**
     * ActiveMQ的管理台地址
     */
    private static final String ACTIVEMQ_URL = "tcp://192.168.1.100:61616";

    /**
     * 队列名
     */
    private static final String QUEUE_NAME = "queue01";

    /**
     * main方法
     * 消费方式1：同步阻塞：receive()，子接收到小时之前（或超时之前），将一直阻塞
     */
    public static void main(String[] args) throws JMSException {
        // 1、创建连接工厂，给定url，用户名密码采用默认的
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、通过连接工厂获得connection，并启动，如果连接不通，可能是防火墙。
        Connection connection = mqConnectionFactory.createConnection();
        connection.start();
        //  3、创建会话session。第一个参数表示事务，第二个参数表示签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地，明确是队列，还是主题
        Queue queue = session.createQueue(QUEUE_NAME);
        Destination destination = session.createQueue(QUEUE_NAME);
        // 5、创建消息的消费者。
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            // messageConsumer.receive(6000);// 参数6000 表示等待消息6秒，不填的话表示一直等
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if(textMessage != null) {
                System.out.println("【消息内容是：】" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();

        System.out.println("【接收消息成功】");
    }

}
