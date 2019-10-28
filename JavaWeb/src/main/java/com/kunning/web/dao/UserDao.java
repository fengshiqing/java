package com.kunning.web.dao;

import com.kunning.web.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

	// 查询用户信息
	List<User> queryUser(User user);

//	@Select("INSERT INTO users(username,password) VALUES(#{username}, #{password})")
	int saveUser(User user);
	
}
