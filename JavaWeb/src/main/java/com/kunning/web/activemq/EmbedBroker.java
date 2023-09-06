package com.kunning.web.activemq;

import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {

    /**
     * main方法
     */
    public static void main(String[] args) throws Exception {

        // ActiveMQ 也支持在vm中通信基于嵌入式的 broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();

        System.out.println("【消息发送到MQ队列中】");
    }

}
