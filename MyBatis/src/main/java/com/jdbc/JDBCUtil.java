package com.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtil {

	/**
	 * <存放jdbc的配置信息>
	 */
	private static final Properties prop;

	static {// 静态代码块
		prop = new Properties();
		try {
			prop.load(new FileReader("jdbc.properties"));// 这种路径的加载方式是"jdbc.properties"直接放在项目根路径下
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <获数据库连接>
	 */
	public static Connection getConnection() throws Exception {
		String driverClass = prop.getProperty("jdbc.driverClassName");
		String url = prop.getProperty("jdbc.url");
		String user = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		System.out.println("driverClass：" + driverClass);
		System.out.println("url：" + url);
		System.out.println("user：" + user);
		System.out.println("password：" + password);
		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		return conn;
	}

	// 测试
	public static void main(String[] args) throws Exception {
		getConnection();
	}

}
