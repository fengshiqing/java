/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.kunning.springboot.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能描述：拦截器。添加请求头信息。
 *
 * @author fengshiqing
 * @since 2020/05/04
 */
// 关于 filter 和 interceptor 的区别，请看这里：https://www.cnblogs.com/junzi2099/p/8022058.html
@Slf4j
@Component
public class CommonInterceptor implements HandlerInterceptor {

    /**
     * 功能描述：调用方法前拦截。
     *
     * @param request 请求
     * @param response 相应
     * @param handler handler对象
     *
     * @return true通过，false直接返回
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @NonNull Object handler) {
        log.info("【preHandle】【开始执行】");

        // 允许跨域，不能放在postHandle内
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }
        log.info("【preHandle】【结束执行】");
        return true;
    }

}
