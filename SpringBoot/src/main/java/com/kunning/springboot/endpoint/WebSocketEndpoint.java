/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.endpoint;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 功能描述：WebSocket终端
 *
 * @author fengshiqing
 * @since 2021-12-17
 */
@Component
@ServerEndpoint(value = "/websocket") // @ServerEndpoint注解是服务端与客户端交互的关键
public class WebSocketEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEndpoint.class);

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static final Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * 功能描述：连接建立成功调用的方法
     *
     * @param session session
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineCount.incrementAndGet(); // 在线数加1
        clients.put(session.getId(), session);
        LOGGER.info("【有新的连接加入了：{}，当前在线人数为：{}】", session.getId(), onlineCount.get());
    }

    /**
     * 功能描述：收到客户端消息后调用的方法
     *
     * @param session session
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage( Session session ,String message) {
        LOGGER.info("【服务端 收到 客户端[{}] 的消息:{}】", session.getId(), message);
        this.sendMessage(session, "来自服务端的问候：Hello, " + message);
    }

    /**
     * 功能描述：连接关闭调用的方法
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        clients.remove(session.getId());
        LOGGER.info("【有一个连接关闭了：{}，当前在线人数为：{}】", session.getId(), onlineCount.get());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.info("【有一个连接发生错误了：{}】", session.getId());
        error.printStackTrace();
    }

    /**
     * 功能描述：服务端发送消息给客户端
     *
     * @param session session
     * @param message message
     */
    private void sendMessage(Session session, String message) {
        LOGGER.info("【服务端 给 客户端[{}] 发送消息：{}】", session.getId(), message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            LOGGER.error("【服务端发送消息给客户端】【发生异常】", e);
        }
    }

    /**
     * 功能描述：定时给客户端发送消息
     */
    @Scheduled(cron="0/3 * * * * ?")
    public void schedule() throws IOException {
        int num = 0;
        LOGGER.info("【定时任务】【客户端数量：{}】", clients.size());
        for (Map.Entry<String, Session> entry : clients.entrySet()) { // 给所有的客户端发送消息
            entry.getValue().getBasicRemote().sendText("【来自服务端 定时任务 的问候:】" + num++);
        }
    }

}
