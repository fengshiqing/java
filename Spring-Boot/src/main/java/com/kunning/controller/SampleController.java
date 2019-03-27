package com.kunning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "SampleController", description = "用户接口")
public class SampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	@ApiOperation(value = "接口文档111", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello111() {
		LOGGER.info("【hello】【开始执行】");

		return "Hello World!";
	}

	@ApiOperation(value = "接口文档222", notes = "")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String hello222() {
		LOGGER.info("【hello】【开始执行】");

		return "Hello World!";
	}

}
