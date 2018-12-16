package com.mybatis.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mybatis.mappers.StudentMapper;
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
		LOGGER.info("开始执行");
		
		SqlSession sqlSession = SqlSessionFactoryUtil.openSession();// 获取sqlsession
		StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);// 获取Mapper
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
