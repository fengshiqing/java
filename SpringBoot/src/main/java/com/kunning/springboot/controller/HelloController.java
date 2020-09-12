package com.kunning.springboot.controller;

import com.kunning.springboot.Handler.MyException;
import com.kunning.springboot.service.UserService;
import com.kunning.springboot.servicecomb.HelloEndPoint;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        return "hello 你好, " + request.getRemoteUser();
    }

    @RequestMapping("/angular/hello")
    public Map<String, String> helloAngular(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(16);
        map.put("id", "1");
        map.put("name", "冯仕清");
        map.put("age", "29");
        return map;
    }

    @RequestMapping("/hello1")
    public String hello1() throws MyException  {
        throw new MyException("发生错误2");
//        return "hello1, " + userService.getUsername();
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

    @RpcReference(microserviceName = "ServiceComb_fengshiqing", schemaId = "hello")
    private HelloEndPoint helloEndPoint;

    @RequestMapping("/servicecomb/invokehello")
    public String invokeServiceComb() {
        return helloEndPoint.hello();
    }

}
