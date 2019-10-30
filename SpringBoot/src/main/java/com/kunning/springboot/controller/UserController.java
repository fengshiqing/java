package com.kunning.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kunning.springboot.pojo.User;
import com.kunning.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "UserController", description = "用户接口")
public class UserController {
	
	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
    private UserService userService;

	@ApiOperation(value = "用户接口", notes = "查看所有用户")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String queryAllUser() {
		LOGGER.info("【queryAllUser】【开始执行】");

		List<User> userList = userService.queryAllUser();
		LOGGER.info("【参数：】【userList:{}】", userList);
		
		return "【queryAllUser - Hello World!】";
	}
	
}
