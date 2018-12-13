package com.java1234.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.java1234.mappers.StudentMapper;
import com.java1234.model.Student;
import com.java1234.util.SqlSessionFactoryUtil;

public class StudentTest2 {

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
	public void testAdd() {
		logger.info("添加学生");
		Student student = new Student("王五", 13);
		int result = studentMapper.add(student);
		sqlSession.commit();
		if (result > 0) {
			logger.info("添加成功");
		}
	}

//	@Test
//	public void testUpdate() {
//		logger.info("修改学生");
//		Student student = new Student(12, "王五", 100);
//		int updateNum = studentMapper.update(student);
//		sqlSession.commit();
//		logger.info("updateNum：" + updateNum);
//	}
//	
//	@Test
//	public void testDelete() {
//		logger.info("删除学生");
//		studentMapper.delete(13);
//		sqlSession.commit();
//	}
//	
//	@Test
//	public void testFindById() {
//		logger.info("ͨ通过ID查找学生");
//		Student student = studentMapper.findById111(1);
//		System.out.println(student);
//	}
//	
//	@Test
//	public void testFind() {
//		logger.info("查找所有学生");
//		List<Student> studnetList = studentMapper.find();
//		for(Student s : studnetList) {
//			System.out.println(s);
//		}
//		logger.info("打印所有学生：" + studnetList.toString());
//	}

//	@Test
//	public void insertStudents() {
//		Student student = new Student();
//		student.setAge(14);
//		student.setName("张三14");
//		student.setRemark("很长的文本。。。");
//		byte[] pic = null;
//		File file = new File("C://cat.gif");
//		try {
//			InputStream inputStream = new FileInputStream(file);
//			pic = new byte[inputStream.available()];
//			inputStream.read(pic);// 把（二进制形式的）输入流写到到pic数组里
//			inputStream.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		student.setPic(pic);
//		int insertNum = studentMapper.insertStudents(student);
//		sqlSession.commit();
//		logger.info("insertNum：" + insertNum);
//	}

//	@Test
//	public void queryStudents() {
//		logger.info("根据Id查询学生信息");
//		Student student = studentMapper.queryStudentById(12);
//		logger.info("student：" + student);
//
//		byte[] pic = student.getPic();
//		File file = new File("D://cat2.gif");
//		try {
//			OutputStream outputStream = new FileOutputStream(file);
//			outputStream.write(pic);// 把pic字节读到file文件中
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	@Test
//	public void queryStudentRowBound() {
//		int offset = 0;
//		int limit = 3;
//		RowBounds rowBounds = new RowBounds(offset, limit);
//		List<Student> studentList = studentMapper.queryStudentRowBound(rowBounds);
//		logger.info("studentList" + studentList);
//		for(Student student : studentList) {
//			System.out.println(student);
//		}
//	}
//	

//	@Test
//	public void queryStudentLimit() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("start", 3);
//		map.put("size", 3);
//		List<Student> studentList = studentMapper.queryStudentLimit(map);
//		logger.info("studentList" + studentList);
//		for(Student student : studentList) {
//			System.out.println(student);
//		}
//	}

}
