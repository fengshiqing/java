package com.kunning.web.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

	private static Properties prop = null;

	/*
	 * 功能描述：用类加载器加载配置文件。只在加载类时执行一次
	 */
	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(JDBCUtils.class.getClassLoader().getResource("jdbc.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);// 往上抛异常
		}
	}

	/**
	 * 私有化构造函数
	 */
	private JDBCUtils() {
	}

	/**
	 * 功能描述：获取连接
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = prop.getProperty("driver");// 值：com.mysql.jdbc.Driver
		String url = prop.getProperty("url");// 值：jdbc:mysql:///fengshiqing
		String user = prop.getProperty("username");// 值：root
		String password = prop.getProperty("password");// 值：feng234800

		Class.forName(driver);// 1、注册数据库驱动
		Connection connection = DriverManager.getConnection(url, user, password);// 2、获取连接

		return connection;
	}

	/**
	 * 功能描述：关闭连接
	 */
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
