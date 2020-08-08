package com.kunning.springboot.controller;

import com.kunning.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello 你好, " + request.getRemoteUser();
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1, " + userService.getUsername();
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "hello2, " + userService.getUsername();
    }

    @RequestMapping("/hello3")
    public String hello3() {
        return "hello3, " + userService.getUsername();
    }

    @RequestMapping("hello/hello111")
    public String hello111() {
        return "hello/hello111, " + userService.getUsername();
    }

    @RequestMapping("/hello/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')") // 指定访问级别的角色
    public String helloAdmin() {
        return "helloAdmin, " + userService.getUsername();
    }

    @RequestMapping("/hello/helloUser")
    @PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser() {
        return "helloUser, " + userService.getUsername();
    }

    @RequestMapping("/hello/session")
    public String session(HttpSession session) {
        String username = (String) session.getAttribute("_user");
        return "helloUser," + username;
    }

}
