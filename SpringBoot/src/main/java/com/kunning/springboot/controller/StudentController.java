package com.kunning.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "StudentController", description = "学生接口")
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@ApiOperation(value = "接口文档111", notes = "")
	@RequestMapping(value = "/student/query", method = RequestMethod.GET)
	public Object hello111() {
		LOGGER.info("【hello】【开始执行】");

		return "Hello World!";
	}

	@ApiOperation(value = "接口文档222", notes = "")
	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public Object hello222() {
		LOGGER.info("【hello】【开始执行】");

		return "Hello World!";
	}

}
