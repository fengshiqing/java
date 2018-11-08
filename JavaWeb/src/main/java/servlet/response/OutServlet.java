package servlet.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 解决乱码问题
 */
public class OutServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getOutputStream().write("中国".getBytes("utf-8"));
//		response.setHeader("Content-Type", "text/html;charset=utf-8");
		
		//response.setCharacterEncoding("utf-8");
		//response.setHeader("Content-Type", "text/html;charset=utf-8");
		
		response.setCharacterEncoding("utf-8");//给服务器设置编码，这一行可以省写
		response.setContentType("text/html;charset=utf-8");//给浏览器设置编码
		response.getWriter().write("中国");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
