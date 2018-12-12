package com.java1234.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.java1234.model.Student;

public interface StudentMapper {
	
	public int add(Student student);
	
	public int update(Student student);
	
	public int delete(Integer id);
	
	public Student findById111(Integer id);// 根据id来查询学生
	
	public List<Student> find();// 查询全部学生
	
	public Student findStudentWithAddress(Integer id);
	
	public int insertStudents(Student student);// 插入一天学生记录，有个字段存图片的
	public Student queryStudentById(int id);// 根据Id查询学生信息。
	
	// 分页查询——逻辑分页
	public List<Student> queryStudentRowBound(RowBounds rowBounds);// 根据Id查询学生信息。
	// 分页——物理分页
	public List<Student> queryStudentLimit(Map<String, Object> map);// 根据Id查询学生信息。
	
}
