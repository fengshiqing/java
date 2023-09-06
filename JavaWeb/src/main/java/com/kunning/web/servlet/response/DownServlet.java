package com.kunning.web.servlet.response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * response实现文件下载
 */
@WebServlet(urlPatterns = { "/servlet/DownServlet" })
// http://localhost:8080/JavaWeb/servlet/DownServlet
public class DownServlet extends HttpServlet {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置消息头，用于下载
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("美女.jpg", "utf-8"));
		System.out.println(this.getServletContext().getRealPath(""));
		System.out.println(this.getServletContext().getRealPath("/"));
		System.out.println(this.getServletContext().getRealPath("1.jpg"));
		System.out.println(this.getServletContext().getRealPath("/1.jpg"));
		
		InputStream in = new FileInputStream(this.getServletContext().getRealPath("1.jpg"));
		OutputStream out = response.getOutputStream();// 获取相应输出流
		
		byte[] bs = new byte[1024];
		int i = 0;
		while((i=in.read(bs))!=-1) {
			out.write(bs,0,i);
		}
		
		in.close();
		//out.close();//这个不用关闭
	}
	
	//URL编码、解码
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "中国";
		String str2 = URLEncoder.encode(str, "utf-8");//将“中国”编码
		System.out.println(str2);
		String str3 = URLDecoder.decode("%E4%B8%AD%E5%9B%BD", "utf-8");//将“中国”解码
		System.out.println(str3);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
