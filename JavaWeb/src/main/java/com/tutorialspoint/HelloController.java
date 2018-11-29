package com.tutorialspoint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <包扫描时多个包内有相同名字的bean导致报错>
 */
@Controller(value = "HelloController111")
@RequestMapping("/hello")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}

}
