package com.kunning.springboot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：拦截器。添加请求头信息。
 * 关于 filter 和 interceptor 的区别，请看这里：https://www.cnblogs.com/junzi2099/p/8022058.html
 *
 * @author fengshiqing
 * @since 2020/05/04
 */
@Component
public class CommonIntercepter implements HandlerInterceptor {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonIntercepter.class);

    /**
     *
     * @param request 请求
     * @param response 相应
     * @param handler handler对象
     * @return true通过，false直接返回
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.info("【preHandle】【开始执行】");

        //允许跨域，不能放在postHandle内
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }
        LOGGER.info("【preHandle】【结束执行】");
        return true;
    }
}
