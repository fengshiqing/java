package com.kunning.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "接口文档示例")
public class SwaggerController {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerController.class);

	@ApiOperation(value = "接口文档1", notes = "note信息1")
	@GetMapping(value = "/接口1路径")
	public String hello111() {
		LOGGER.info("【hello】【开始执行】");
		return "Hello World!";
	}

	@ApiOperation(value = "接口文档2", notes = "note信息2")
	@RequestMapping(value = "/接口2路径", method = {RequestMethod.GET, RequestMethod.POST})
	public String hello222() {
		LOGGER.info("【hello】【开始执行】");
		return "Hello World111!";
	}

}
