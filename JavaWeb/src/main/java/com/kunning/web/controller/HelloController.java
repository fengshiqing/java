package com.kunning.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Value("${env.name}")
    private String envName;

    @RequestMapping(value = "/SpringMVC/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        LOGGER.info("【printHello】【开始执行】");
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getResource(""));
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getResource("/"));
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getClassLoader().getResource(""));

        model.addAttribute("message", "Hello Spring MVC Framework!!!");
        return "hello";
    }

    @RequestMapping(value = "/Var/{page}", method = RequestMethod.GET)
    public String printPage(@PathVariable String page, ModelMap model) {
        LOGGER.info("【printHello】【开始执行】");
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getResource(""));
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getResource("/"));
        LOGGER.info("【printHello】【开始执行】【路径：{}】", HelloController.class.getClassLoader().getResource(""));

        model.addAttribute("message", page);
        return "hello";
    }

    @RequestMapping(value = "/env/readProp")
    public void readProp() {
        System.out.println("【环境变量：{}】" + envName);
    }

}
