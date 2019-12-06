package com.kunning.web.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer2 {

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
    public static void main(String[] args) throws JMSException, IOException {
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

//        messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if (message != null && message instanceof TextMessage) {
//                    TextMessage textMessage = (TextMessage) message;
//                    try {
//                        System.out.println("【MessageListener接收的消息内容是：】" + textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } );

        // 上面注释掉的代码，可以用lambda表达式代替
        messageConsumer.setMessageListener((Message message) -> {
            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("【MessageListener接收的消息内容是：】" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        System.in.read();// 保持控制台不灭
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
