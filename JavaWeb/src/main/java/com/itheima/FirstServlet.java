package com.itheima;
//import java.io.*;
import javax.servlet.*;

public class FirstServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest req, ServletResponse res) throws ServletException, java.io.IOException {
		res.getWriter().write("hello Servlet!");
	}
}