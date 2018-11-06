package com.itheima.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.User;
import com.itheima.exception.MsgException;
import com.itheima.service.UserService;

/**
 * <点击“注册“按钮后，执行此servlet>
 * @author kun
 *
 */
@WebServlet(urlPatterns = { "/servlet/RegistServlet" })
public class RegistServlet extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			// 1.检验验证码
			String valistr = request.getParameter("valistr");
			String valistr2 = (String) request.getSession().getAttribute("valistr");
			if (valistr == null || valistr2 == null || !valistr.equals(valistr2)) {
				request.setAttribute("msg", "验证码不正确！");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
				return;
			}
			// 2.封装数据校验数据
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.checkValue();
			// 3.调用Service中的方法添加用户
			UserService service = new UserService();
			service.registUser(user);
			// 4.登录用户
			request.getSession().setAttribute("user", user);
			// 5.提示注册成功3秒回到主页
			response.getWriter().write("恭喜您注册成功！3秒回到主页....");
			response.setHeader("refresh", "3;url=" + request.getContextPath() + "/index.jsp");
		} catch (MsgException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
