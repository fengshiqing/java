package com.kunning.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello 你好";
    }

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')") // 指定访问级别的角色
    public String helloAdmin(){
        return "helloAdmin";
    }

    @GetMapping("/helloUser")
    @PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser(){
        return "helloUser";
    }

    @GetMapping("/session")
    public String session(HttpSession session){
        String username = (String) session.getAttribute("_user");
        return "helloUser," + username;
    }

}
