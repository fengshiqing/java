package com.kunning.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/student/query", method = RequestMethod.GET)
    public Object hello111() {
        LOGGER.info("【hello】【开始执行】");

        return "Hello World!";
    }

    @RequestMapping(value = "/student/update", method = RequestMethod.POST)
    public Object hello222() {
        LOGGER.info("【hello】【开始执行】");

        return "Hello World!";
    }

}
