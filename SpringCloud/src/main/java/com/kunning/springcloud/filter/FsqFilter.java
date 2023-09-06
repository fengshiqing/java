/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：自定义 Filter 过滤器
 *
 * @author 冯仕清
 * @since 2019/12/15
 */
@Slf4j // topic属性是定义LOG记录器的名字的，默认是类名.class，比如：Filter_01.class
public class FsqFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("【Filter_01初始化了】");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("【Filter_01拦截到了请求】");
        chain.doFilter(request, response);
        log.info("【Filter_01已经放行了请求】");
    }

    @Override
    public void destroy() {
        System.out.println("【Filter_01销毁了】");
    }

}
