package com.kunning.springboot.controller;


import com.kunning.springboot.pojo.ResultData;
import com.kunning.springboot.pojo.User;
import com.kunning.springboot.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录接口文档")
public class LoginController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * @param
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口", notes = "登录账号")
    public void login() {
        LOGGER.info("【登录成功...】");
    }

    /**
     * 功能描述：注册账号
     *
     * @param user 用户信息
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "注册接口", notes = "注册账号")
    public ResultData register(@RequestBody User user) {
        int num = loginService.insert(user);
        LOGGER.info("【注册成功】{}", num);
        return new ResultData("1234798965");
    }

}
