package com.kunning.springboot.controller;

import java.util.List;

import com.kunning.springboot.pojo.ResponseApi;
import com.kunning.springboot.pojo.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kunning.springboot.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 功能描述：用户登录、注册、退出服务；增删改查用户信息
 *
 * @author 冯仕清
 * @since 2019-10-1
 */
@Controller
@ResponseBody
@Api(tags = "用户账号服务")
public class UserController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 功能描述：登录
     *
     * @param request request对象
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signin(HttpServletRequest request, HttpSession session) {
        LOGGER.info("【signin】【start】");

        session.setAttribute("_user", request.getHeaderNames());

        System.out.println(request.getAuthType());
        System.out.println(request.getHeaderNames());

        LOGGER.info("【signin】【end】");
        return new ResponseApi(200, "登录成功");
    }

    /**
     * 功能描述：注册账号
     *
     * @param userDto 用户信息
     */
    @ApiOperation(value = "注册接口", notes = "注册账号")
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signup(@RequestBody UserDto userDto) {
        LOGGER.info("【signup】【start】");
        int num = userService.insert(userDto);
        LOGGER.info("【注册成功】{}", num);
        return new ResponseApi(200, "注册成功");
    }

    /**
     * 功能描述：退出登录
     *
     * @param userDto 用户信息
     */
    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping(value = "/signout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseApi signout(@RequestBody UserDto userDto, HttpSession session) {
        session.invalidate();
        LOGGER.info("【signout】【start】【username:{}】", userDto.getUsername());
        return new ResponseApi(200, "退出成功");
    }

    @ApiOperation(value = "用户接口", notes = "查看所有用户")
    @GetMapping(value = "/user")
    public String queryAllUser() {
        LOGGER.info("【queryAllUser】【开始执行】");

        List<UserDto> userDtoList = userService.queryAllUser();
        LOGGER.info("【参数：】【userList:{}】", userDtoList);

        LOGGER.info("【signout】【end】");
        return "【queryAllUser - Hello World!】";
    }

}
