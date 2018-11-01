package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 继承 GenericServlet，也可以继承 HttpServlet
 */
// Servlet3.0可以不需要web.xml了，新增了注解支持
// name属性等同于 <servlet-name> 
// urlPatterns：指定一组 Servlet 的 URL 匹配模式。等价于 <url-pattern> 标签。
@WebServlet(urlPatterns = { "/GenericServlet/FirstServlet", "/hello" }, loadOnStartup = 1, initParams = {
		@WebInitParam(name = "name", value = "hotusm") })
public class FirstServlet extends GenericServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * GenericServlet需要实现service方法
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.getWriter().write("hello Servlet!");// getWriter()返回一个字节输出流PrintWriter的对象

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();// 字符输出
		// response.getOutputStream();//流输出
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello World!</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Hello World!</h1>");
		out.println("</body>");
		out.println("</html>");
	}

}
