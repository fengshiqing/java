package com.itheima.factory;

import java.io.FileReader;
import java.util.Properties;

import com.itheima.dao.UserDao;

public class DaoFactory {

	// 单例模式，私有化构造函数，不允许在其他地方new出此对象，
	private DaoFactory() {
	}

	// 静态私有的单例对象
	private static DaoFactory factory = new DaoFactory();

	private static Properties prop = null;

	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(DaoFactory.class.getClassLoader().getResource("config.properties").getPath()));// 加载配置文件
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 对外提供使用单例对象的接口方法
	public static DaoFactory getFactory() {
		return factory;
	}

	public UserDao getDao() {
		try {
			String clazz = prop.getProperty("UserDao");
			return (UserDao) Class.forName(clazz).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
