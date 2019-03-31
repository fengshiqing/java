package com.kunning.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <自定义监听器>
 */
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// tomcat服务器已启动，就开始加载webapps里的应用，加载好一个应用就创建好其对应的ServletContext
		System.out.println("【ServletContext 对象被创建出来了】" + sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("【ServletContext 对象被销毁了】" + sce.getServletContext());
	}

}
