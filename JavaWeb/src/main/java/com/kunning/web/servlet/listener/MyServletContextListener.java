package com.kunning.web.servlet.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 功能描述：自定义 ServletContext 监听器
 *
 * @author 冯仕清
 * @since 2019/12/15
 */
public class MyServletContextListener implements ServletContextListener {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// tomcat服务器已启动，就开始加载webapps里的应用，加载好一个应用就创建好其对应的ServletContext
		LOGGER.info("【ServletContext 对象被创建出来了】【{}】", sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("【ServletContext 对象被销毁了】【{}】", sce.getServletContext());
	}

}
