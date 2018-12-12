package com.java1234.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.mappers.StudentMapper;
import com.java1234.model.Student;
import com.java1234.util.SqlSessionFactoryUtil;

public class StudentTest3 {

	private static Logger logger = Logger.getLogger(StudentTest.class);
	private SqlSession sqlSession = null;
	StudentMapper studentMapper = null;
	
	@Before
	public void setUp() throws Exception {
		sqlSession = SqlSessionFactoryUtil.openSession();
		studentMapper = sqlSession.getMapper(StudentMapper.class);
	}

	@After
	public void tearDown() throws Exception {
		sqlSession.close();
	}

	@Test
	public void testFindStudentWithAddress() {
		logger.info("查询学生（带地址）");
		Student student = studentMapper.findStudentWithAddress(1);
		//sqlSession.commit();
		System.out.println(student);
	}

}
