package com.kunning.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        return "hello 你好 " + request.getRemoteUser();
    }
    @RequestMapping("/hello1")
    public String hello1() {
        //这边我们,默认是返到templates下的login.html
        return "hello1";
    }
    @RequestMapping("/hello2")
    public String hello2() {
        //这边我们,默认是返到templates下的login.html
        return "hello2";
    }
    @RequestMapping("/hello3")
    public String hello3() {
        //这边我们,默认是返到templates下的login.html
        return "hello3";
    }

    @RequestMapping("hello/hello111")
    public String hello111() {
        //这边我们,默认是返到templates下的login.html
        return "hello/hello111";
    }

    @RequestMapping("/hello/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')") // 指定访问级别的角色
    public String helloAdmin(){
        return "helloAdmin";
    }

    @RequestMapping("/hello/helloUser")
    @PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser(){
        return "helloUser";
    }

    @RequestMapping("/hello/session")
    public String session(HttpSession session){
        String username = (String) session.getAttribute("_user");
        return "helloUser," + username;
    }

}
