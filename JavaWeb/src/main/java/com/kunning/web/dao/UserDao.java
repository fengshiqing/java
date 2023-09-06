package com.kunning.web.dao;

import com.fengshiqing.common.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

	@Select("INSERT INTO users(username,password) VALUES(#{username}, #{password})")
	int addUser(User user);

	// 查询用户信息
	List<User> queryUser(User user);

}
