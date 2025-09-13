package com.kunning.web.servlet.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取客户机的请求头信息
 */
@WebServlet(urlPatterns = { "/servlet/RequestHeader" })
public class RequestHeader extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");// 告诉服务器用什么码表方式进行编码，这一行可以省写
		response.setContentType("text/html;charset=utf-8");// 告诉浏览器以什么码表进行解码，有了这一句，上面那行代码可以不写。

		PrintWriter out = response.getWriter();
		out.println("<h3>获取客户机的请求头信息</h3>");
		Enumeration<?> e = request.getHeaderNames();// 获取客户机的请求头信息
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getHeader(name);
			out.println(name + " = " + value + "<br>");
		}
		// 获取客户机的信息
		out.println("<h3>解决了乱码问题</h3>");
		out.println("Method: " + request.getMethod() + "</br>"); // 获取请求方式：GET、POST
		out.println("Request URI: " + request.getRequestURI() + "</br>");// 获取 URI 地址
		out.println("Request URL: " + request.getRequestURL() + "</br>");// 获取 URL 地址
		out.println("Protocol: " + request.getProtocol() + "</br>"); // 返回请求request使用的协议 的名称和版本
		out.println("PathInfo: " + request.getPathInfo() + "</br>");//
		out.println("Remote Address: " + request.getRemoteAddr() + "</br>");
		out.println("AuthType：" + request.getAuthType() + "</br>");

		Enumeration<?> e1 = request.getAttributeNames();// 获取客户机的请求中的属性信息，默认是没有属性的，为空
		while (e1.hasMoreElements()) {
			String name = (String) e1.nextElement();
			String value = request.getHeader(name);
			out.println(name + " = " + value + "<br>");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}

}