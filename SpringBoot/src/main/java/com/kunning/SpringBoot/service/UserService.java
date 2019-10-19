package com.kunning.javaSE.service;

import java.util.List;

import com.kunning.javaSE.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunning.javaSE.dao.UserMapper;
import com.kunning.javaSE.pojo.User;

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
