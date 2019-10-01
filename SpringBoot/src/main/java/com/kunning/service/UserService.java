package com.kunning.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunning.controller.UserController;
import com.kunning.dao.UserMapper;
import com.kunning.pojo.User;

@Service("userService")
public class UserService {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserMapper userMapper;

	public List<User> queryAllUser() {
		LOGGER.info("【queryAllUser】【开始执行】");

		List<User> userList = userMapper.getAll();
		LOGGER.info("【参数：】【userList:{}】", userList);

		return userList;
	}

}
