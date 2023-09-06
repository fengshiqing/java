package com.kunning.web.servlet.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 功能描述：自定义 HttpSession 监听器
 *
 * @author 冯仕清
 * @since 2019/12/15
 */
public class MyHttpSessionListener implements HttpSessionListener {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
		LOGGER.info("【Session 对象被创建出来了】" + se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
		LOGGER.info("【Session 对象被销毁了】" + se.getSession());
    }

}
