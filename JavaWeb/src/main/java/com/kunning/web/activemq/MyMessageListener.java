package com.kunning.web.activemq;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class MyMessageListener implements javax.jms.MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message != null && message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
