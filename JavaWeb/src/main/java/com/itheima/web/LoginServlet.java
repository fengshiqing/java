package com.itheima.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.User;
import com.itheima.service.UserService;

/**
 * <点击“登陆“按钮后，执行此servlet>
 * @author kun
 *
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		UserService service = new UserService();
		// 1.获取客户端提交的用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.调用Service中的方法检查用户名密码
		User user = service.isUser(username, password);
		if (user == null) {
			// 3.如果不正确则提示
			request.setAttribute("msg", "用户名密码不正确!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		} else {
			// 4.正确则登录用户后重定向回到主页
			request.getSession().setAttribute("user", user);

			if ("ok".equals(request.getParameter("remname"))) {
				// 如果用户勾选过记住用户则发送cookie另浏览器保存用户名
				Cookie remNameC = new Cookie("remname", URLEncoder.encode(user.getUsername(), "utf-8"));
				remNameC.setPath(request.getContextPath());
				remNameC.setMaxAge(3600 * 24 * 30);
				response.addCookie(remNameC);
			} else {
				// 如果用户没有勾选记住用户名则删除记住用户名的cookie
				Cookie remNameC = new Cookie("remname", "");
				remNameC.setPath(request.getContextPath());
				remNameC.setMaxAge(0);
				response.addCookie(remNameC);
			}

			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
