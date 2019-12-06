package com.kunning.web.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {

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
     */
    public static void main(String[] args) throws JMSException {

        // 1、new出连接工厂，给定url，用户名密码采用默认的
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、创建connection，并启动start，如果连接不通，可能是防火墙。
        Connection connection = mqConnectionFactory.createConnection();
        connection.start();
        //  3、创建会话session。第一个参数表示事务，第二个参数表示签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地（队列 or 主题）
        Queue queue = session.createQueue(QUEUE_NAME);
        // Destination destination = session.createQueue(QUEUE_NAME);

        // 5、创建消息的生产者，放到队列中。
        MessageProducer messageProducer = session.createProducer(queue);
        // 通过使用 messageProducer 生产3条消息，并发送到MQ的队列中。
        for (int i = 1; i<=3; i++) {
            // 6、创建消息
            TextMessage textMessage = session.createTextMessage("【msg---】" + i);// 理解为一个字符串
            // 7、通过 messageProducer 将消息发送给 MQ 队列中
            messageProducer.send(textMessage);
        }
        // 8、关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("【消息发送到MQ队列中】");
    }

}
