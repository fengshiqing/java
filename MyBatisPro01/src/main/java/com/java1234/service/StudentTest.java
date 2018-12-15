package com.java1234.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.java1234.mappers.StudentMapper;
import com.java1234.model.Student;
import com.java1234.util.SqlSessionFactoryUtil;

public class StudentTest {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);
	
	/**
	 * <主方法>
	 */
	public static void main(String[] args) {
		LOGGER.info("开始执行");
		
		SqlSession sqlSession = SqlSessionFactoryUtil.openSession();
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
		Student student = new Student("zhangsan", 11);
		int result = studentMapper.add(student);
		sqlSession.commit();// 注释掉这句话，就没有提交，数据库中也就不会有变化
		sqlSession.close();
		if(result > 0) {
			LOGGER.info("添加成功");
		} else {
			LOGGER.info("添加失败");
		}
	}
}
