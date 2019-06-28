package com.kunning.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {

    /**
     * ActiveMQ的管理台地址
     */
    private static final String ACTIVEMQ_URL = "tcp://192.168.124.16:61616";

    /**
     * 队列名
     */
    private static final String QUEUE_NAME = "queue01";

    /**
     * main方法
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
        // Destination destination = session.createQueue(QUEUE_NAME);

        // 5、创建消息的生产者，放到队列中。
        MessageProducer messageProducer = session.createProducer(queue);
        // 6、通过使用 messageProducer 生产3条消息，并发送到MQ的队列中。
        for (int i = 1; i<=3; i++) {
            // 7、创建消息
            TextMessage textMessage = session.createTextMessage("【msg---】" + i);// 理解为一个字符串
            // 8、通过 messageProducer 将消息发送给 MQ
            messageProducer.send(textMessage);
        }
        // 9、关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("【消息发送到MQ队列中】");
    }

}
