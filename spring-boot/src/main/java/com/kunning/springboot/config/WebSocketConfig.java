/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 功能描述：配置 WebSocket。
 * 首页地址：http://localhost/websocket.html
 * <p>
 * 参卡：https://www.cnblogs.com/xuwenjin/p/12664650.html
 *
 * @author fengshiqing
 * @since 2021-12-17
 */
@Slf4j
@Configuration
public class WebSocketConfig {

    /**
     * 构造函数
     */
    public WebSocketConfig() {
        log.info("【初始化 WebSocket 配置】");
    }

    /**
     * 功能描述：注册 websocket 服务端。
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
