package com.kunning.web.controller;

import com.kunning.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunning.web.dao.UserDao;
import com.kunning.web.pojo.User;

@Controller
public class HelloController {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;
	
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
	
	@RequestMapping(value = "/SpringMVC/ajax")
	@ResponseBody
	public String ajax() {
		LOGGER.info("【ajax】【开始执行】");
		return "testAJAX";
	}
	
	
	@RequestMapping(value = "/SpringMVC/saveUser")
	@ResponseBody
	public String saveUser(User user) {
		LOGGER.info("【saveUser】【开始执行】【请求参数：user:{}】", user);
		int num = userDao.saveUser(user);
		LOGGER.info("【saveUser】【结束执行】【执行结果】【执行成功】【num:{}】", num);		
		return "save success";
	}
	
}
