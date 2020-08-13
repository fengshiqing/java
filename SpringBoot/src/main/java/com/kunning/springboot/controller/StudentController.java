package com.kunning.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@ResponseBody
@Api(tags = "学生接口")
public class StudentController {

    /**
     * 日志
     */
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
