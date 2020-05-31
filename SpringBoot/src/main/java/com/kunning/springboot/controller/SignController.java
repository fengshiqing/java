package com.kunning.springboot.controller;

import com.kunning.springboot.pojo.ResponseApi;
import com.kunning.springboot.pojo.User;
import com.kunning.springboot.service.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述：登录、注册、退出登录
 *
 * @author 冯仕清
 * @since 2019-10-1
 */
@RestController
@Api(tags = "登录/注册 接口文档")
public class SignController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);

    @Autowired
    private SignService loginService;

    /**
     * 功能描述：登录
     *
     * @param request request对象
     */
    @ApiOperation(value = "登录接口", notes = "登录账号")
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signin(HttpServletRequest request) {
        LOGGER.info("【登录成功...】");

        System.out.println(request.getAuthType());
        System.out.println(request.getHeaderNames());

        return new ResponseApi(200, "登录成功");
    }

    /**
     * 功能描述：注册账号
     *
     * @param user 用户信息
     */
    @ApiOperation(value = "注册接口", notes = "注册账号")
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signup(@RequestBody User user) {
        int num = loginService.insert(user);
        LOGGER.info("【注册成功】{}", num);
        return new ResponseApi(200, "注册成功");
    }

    /**
     * 功能描述：退出登录
     *
     * @param user 用户信息
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "/signout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signout(@RequestBody User user) {
        int num = loginService.insert(user);
        LOGGER.info("【注册成功】{}", num);
        return new ResponseApi(200, "退出成功");
    }

}
