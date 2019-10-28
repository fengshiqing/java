package com.kunning.web.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <过滤器>
 * 
 * @author kunning
 *
 */
public class Filter_01 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("【Filter_01初始化了】");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("【Filter_01拦截到了请求】");
		chain.doFilter(request, response);
		System.out.println("【Filter_01放行了请求】");
	}

	@Override
	public void destroy() {
		System.out.println("【Filter_01销毁了】");
	}

}
