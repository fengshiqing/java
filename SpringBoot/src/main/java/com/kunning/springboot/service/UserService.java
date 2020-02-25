package com.kunning.springboot.service;

import java.util.List;

import com.kunning.springboot.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.User;

@Service("userService")
public class UserService {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDao userMapper;

	public List<User> queryAllUser() {
		LOGGER.info("【queryAllUser】【开始执行】");

		List<User> userList = userMapper.queryAllUser();
		LOGGER.info("【参数：】【userList:{}】", userList);

		return userList;
	}

}
