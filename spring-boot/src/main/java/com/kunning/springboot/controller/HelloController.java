package com.kunning.springboot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengshiqing.common.BizException;

/**
 * 功能描述：Hello World
 *
 * @author fengshiqing
 * @since 2021-12-17
 */
@Controller
@ResponseBody
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        LOGGER.info("【HelloController】");
        return "hello 你好, " + request.getRemoteUser();
    }

    @RequestMapping("/angular/hello")
    public Map<String, String> helloAngular() {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", "1");
        map.put("name", "冯仕清");
        map.put("age", "29");
        return map;
    }

    @RequestMapping("/hello1")
    public String hello1() throws BizException {
        throw new BizException(500001, "发生错误500001");
        // return "hello1, " + userService.getUsername();
    }

    @RequestMapping("/hello2")
    public String hello2() throws BizException {
        throw new BizException(500002, "发生错误500002");
        // return "hello2, " + userService.getUsername();
    }

    @RequestMapping("/hello3")
    public String hello3() throws IOException {
        throw new IOException("发生错误500005");
        // return "hello3, " + userService.getUsername();
    }

    @RequestMapping("/hello/session")
    public String session(HttpSession session) {
        String username = (String) session.getAttribute("_user");
        return "helloUser," + username;
    }

}
