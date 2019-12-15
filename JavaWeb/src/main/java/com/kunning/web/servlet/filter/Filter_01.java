package com.kunning.web.servlet.filter;

import com.kunning.web.servlet.listener.MyHttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 功能描述：自定义 Filter 过滤器
 *
 * @author 冯仕清
 * @since 2019/12/15
 */
public class Filter_01 implements Filter {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Filter_01.class);

	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.info("【Filter_01初始化了】");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("【Filter_01拦截到了请求】");
		chain.doFilter(request, response);
		LOGGER.info("【Filter_01已经放行了请求】");
	}

	@Override
	public void destroy() {
		System.out.println("【Filter_01销毁了】");
	}

}
