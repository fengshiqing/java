package com.kunning.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
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


}
