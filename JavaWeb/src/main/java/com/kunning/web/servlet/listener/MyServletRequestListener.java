package com.kunning.web.servlet.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 功能描述：自定义 ServletRequest 监听器
 *
 * @author 冯仕清
 * @since 2019/12/15
 */
public class MyServletRequestListener implements ServletRequestListener {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
		LOGGER.info("【Request 对象被创建出来了】" + sre.getServletRequest());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
		LOGGER.info("【Request 对象被创建出来了】" + sre.getServletRequest());
    }

}
