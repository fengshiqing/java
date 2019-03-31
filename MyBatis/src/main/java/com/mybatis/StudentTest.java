package com.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis.model.Student;
import com.mybatis.util.SqlSessionFactoryUtil;

public class StudentTest {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);

	/**
	 * <主方法>
	 */
	public static void main(String[] args) {
		LOGGER.info("【开始执行】");

		// 步骤一：获取Session
		SqlSession sqlSession = SqlSessionFactoryUtil.openSession();// 获取sqlsession
		
		// 步骤二：组装数据
		Student student = new Student("zhangsan", 11);

		// 步骤三：执行sql语句
		// 执行sql方式一：
		int result = sqlSession.insert("com.mybatis.mappers.StudentMapper.add", student);// 这种方式可以不写dao层接口，直接调用。

		// 执行sql方二：
//		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);// 这种方式需要dao层接口。sql语句甚至可以写在dao层接口的方法上。
//		int result = studentMapper.add(student);

		// 步骤四：commit、close
		sqlSession.commit();// 注释掉这句话，就没有提交，数据库中也就不会有变化
		sqlSession.close();
		if (result > 0) {
			LOGGER.info("【添加成功】");
		} else {
			LOGGER.info("【添加失败】");
		}
	}
}
