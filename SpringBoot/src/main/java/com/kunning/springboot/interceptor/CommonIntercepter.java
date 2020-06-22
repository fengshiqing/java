package com.kunning.springboot.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
     * 功能描述：调用方法前拦截。
     *
     * @param request  请求
     * @param response 相应
     * @param handler  handler对象
     *
     * @return true通过，false直接返回
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("【preHandle】【开始执行】");

        // 验证是否登录。 使用了 Spring Security 这些代码就不用自己写了
//        String username = (String) request.getSession().getAttribute("");
//        if (username == null) {
//            this.writeContent(response, "tips111:请先登录");
//            LOGGER.info("【请先登录】");
//            return true;
//        }
//        if  (!"某个特定值".equals(username)) {
//            this.writeContent(response, "没有权限");
//            return false;
//        }

        //允许跨域，不能放在postHandle内
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
        }
        LOGGER.info("【preHandle】【结束执行】");
        return true;
    }

    /**
     * 功能描述：调用方法后拦截。
     *
     * @param request  请求
     * @param response 相应
     * @param handler  handler对象
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.flush();
        writer.close();
    }

}
