/**
 * 
 */
package com.kun;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kun
 *
 */
public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>Request Information Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Request Information Example</h3>");

		out.println("Method: " + request.getMethod() + "</br>"); // 获取请求方式：GET、POST
		out.println("Request URI: " + request.getRequestURI() + "</br>");// 获取 URI 地址
		out.println("Request URL: " + request.getRequestURL() + "</br>");// 获取 URL 地址
		out.println("Protocol: " + request.getProtocol() + "</br>"); // 返回请求request使用的协议 的名称和版本
		out.println("PathInfo: " + request.getPathInfo() + "</br>");//
		out.println("Remote Address: " + request.getRemoteAddr() + "</br>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doGet(request, response);
	}
	
}
