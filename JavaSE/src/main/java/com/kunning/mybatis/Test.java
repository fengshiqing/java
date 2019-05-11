package com.kunning.mybatis;

import com.kunning.mybatis.dao.UserDao;
import com.kunning.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kunning.mybatis.util.SqlSessionFactoryUtil;

import java.util.List;

public class Test {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

	/**
	 * <主方法>
	 */
	public static void main(String[] args) {
		LOGGER.info("【开始执行】");

		// 步骤一：获取Session
		SqlSession sqlSession = SqlSessionFactoryUtil.openSession();// 获取sqlsession
		
		// 步骤二：组装数据
//		User user = new User("fengshiqing", "feng.1029");

		// 步骤三：执行sql语句
		// 方式一：这种方式可以不写dao层接口，直接调用。但是必须将sqlMap的xml文件配置到mybatis-config.xml文件中。
//        List<User> userList = sqlSession.selectList("com.kunning.mybatis.dao.UserDao.getAll");

		// 方式二：这种方式需要dao层接口。sql语句甚至可以写在dao层接口的方法上（这样就不必将sqlMap的xml文件配置到mybatis-config.xml文件中。）。
		UserDao userDao = sqlSession.getMapper(UserDao.class);
		List<User> userList = userDao.getAll();

		// 步骤四：commit、close
		sqlSession.commit();// 注释掉这句话，就没有提交，数据库中也就不会有变化
		sqlSession.close();
		if (userList.size() > 0) {
			LOGGER.info("【添加成功】");
		} else {
			LOGGER.info("【添加失败】");
		}
	}
}
