/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.kunning.springboot.config.filter;


import com.fengshiqing.common.Constant.SysConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * 功能描述：Request请求的上下文中添加 traceId、用户信息。
 *
 * @author 冯仕清
 * @since 2025-07-04
 */
@AllArgsConstructor
@Slf4j
@Component
public class RequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws IOException, ServletException {

        // 设置 日志链路追踪
        String traceId = request.getHeader(SysConstant.TRACE_ID);
        if (!StringUtils.hasText(traceId)) { // 请求有 TraceId，则直接使用
            traceId = UUID.randomUUID().toString().replace("-", "");
        }
        MDC.put(SysConstant.TRACE_ID, traceId);

        // 设置 用户名/当前登陆人
        String username = request.getHeader(SysConstant.USER_NAME);
        if (StringUtils.hasText(username)) {
            MDC.put(SysConstant.USER_NAME, username);
        }

        try {
            filterChain.doFilter(request, response); // 执行业务逻辑
        } finally {
            MDC.clear();
        }
    }

}
