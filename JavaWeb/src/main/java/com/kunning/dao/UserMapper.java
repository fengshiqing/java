package com.kunning.dao;

import org.springframework.stereotype.Repository;

import com.kunning.pojo.User;

@Repository("useMapper")
public interface UserMapper {

//	@Select("INSERT INTO users(username,password,nickname,email) VALUES(#{username}, #{password}, #{nickname}, #{email})")
	int saveUser(User user);
	
}
