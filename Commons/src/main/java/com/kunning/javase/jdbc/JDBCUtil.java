package com.kunning.javase.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 功能描述：JDBC连接工具类
 *
 * @author 冯仕清
 * @since 2016/03/20 21:23
 */
public class JDBCUtil {

	/**
	 * 存放jdbc的配置信息
	 */
	private static final Properties prop;

	static {// 静态代码块
		prop = new Properties();
		try {
			prop.load(new FileReader("jdbc.properties"));// 这种路径的加载方式是"jdbc.properties"直接放在项目根路径下
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：获数据库连接
	 */
	public static Connection getConnection() throws Exception {
		String driverClassName = prop.getProperty("jdbc.driverClassName");// 驱动类全限定名
		String url = prop.getProperty("jdbc.url");// URL
		String user = prop.getProperty("jdbc.username");// 用户名
		String password = prop.getProperty("jdbc.password");// 密码
		System.out.println("drivdriverClassNameerClass：" + driverClassName);
		System.out.println("url：" + url);
		System.out.println("user：" + user);
		System.out.println("password：" + password);
		Class.forName(driverClassName);// 注册数据库驱动
		Connection conn = DriverManager.getConnection(url, user, password);// 获取数据库连接
		System.out.println(conn);
		return conn;
	}

	// 测试
	public static void main(String[] args) throws Exception {
		Connection conn = JDBCUtil.getConnection();
		System.out.println(conn);
	}

}
