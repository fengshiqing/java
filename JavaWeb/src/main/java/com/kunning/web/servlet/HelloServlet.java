package com.kunning.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承 HttpServlet
 */
@WebServlet(urlPatterns = {"/servlet/HelloServlet"})
public class HelloServlet extends HttpServlet {

    /**
     * 继承HttpServlet需要重载doGet、doPost
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<title>HelloServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>HelloServlet</h3>");
        out.println("<h1>继承 HttpServlet 抽象类</h1>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

}
