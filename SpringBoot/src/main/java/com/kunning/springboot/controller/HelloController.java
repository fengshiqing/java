package com.kunning.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        return "hello 你好 " + request.getRemoteUser();
    }

    @RequestMapping("/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')") // 指定访问级别的角色
    public String helloAdmin(){
        return "helloAdmin";
    }

    @RequestMapping("/helloUser")
    @PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser(){
        return "helloUser";
    }

    @RequestMapping("/session")
    public String session(HttpSession session){
        String username = (String) session.getAttribute("_user");
        return "helloUser," + username;
    }

}
