package com.kunning.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 功能描述：学习servlet。
 * 继承 GenericServlet，也可以继承 HttpServlet
 *
 * @author 冯仕清
 * @since 2014/06/01
 */
// Servlet3.0可以不需要web.xml了，新增了注解支持
// name属性等同于 <servlet-name> 
// urlPatterns：指定一组 Servlet 的 URL 匹配模式。等价于 <url-pattern> 标签。可以有多个路径来对应同一个class
// 特别注意：路径前的撇斜杠“/”是必须的！！！
@WebServlet(urlPatterns = {"/servlet/FirstServlet", "/FirstServlet"}, loadOnStartup = 1,
        initParams = {@WebInitParam(name = "name", value = "fengshiqing")})
public class FirstServlet extends GenericServlet {

    /**
     * GenericServlet需要实现service方法
     *
     * @param req 请求
     * @param res 相应
     * @throws IOException IO异常
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.getWriter().write("This is my FirstServlet!");// getWriter()返回一个字节输出流PrintWriter的对象

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();// 字符输出
        // response.getOutputStream();//流输出
        out.println("<html>");
        out.println("<head>");
        out.println("<title>FirstServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>This is my FirstServlet!</h1>");
        out.println("<h1>继承 GenericServlet 抽象类</h1>");
        out.println("</body>");
        out.println("</html>");
    }

}
