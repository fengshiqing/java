package com.itheima.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	
	private static Properties prop = null;
	
	// 私有构造函数。一般来说，工具类都不让直接new的，这是规范，方法都是static的，通过类名直接调用
	private JDBCUtils() {
	}
	
	static{// 这个配置文件被解析一次就够了。
		try{
			prop = new Properties();
			// 为了增加可移植性，尽量不用路径的形式，用类加载器
			prop.load(new FileReader(JDBCUtils.class.getClassLoader().getResource("config.properties").getPath()));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);// 往上抛异常
		}
	}
	
	/**
	 * 获取连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		// 1.注册数据库驱动
		Class.forName(prop.getProperty("driver"));
		
		// 2.获取连接
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
	}
	
	/**
	 * 关闭连接
	 */
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if(stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				stat = null;
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	
	}
}
