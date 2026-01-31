package com.kunning.springboot.controller;

import com.kunning.springboot.model.UserDto;
import com.kunning.springboot.model.resp.Resp;
import com.kunning.springboot.model.resp.RespData;
import com.kunning.springboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能描述：用户登录、注册、退出服务；增删改查用户信息
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
@Controller
@ResponseBody
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    /**
     * 构造器
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 功能描述：登录
     *
     * @param request request对象
     */
    @RequestMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resp signin(HttpServletRequest request, HttpSession session) {
        LOGGER.info("【signin】【start】");

        session.setAttribute("_user", request.getHeaderNames());

        System.out.println(request.getAuthType());
        System.out.println(request.getHeaderNames());

        LOGGER.info("【signin】【end】");
        return new Resp(200, "登录成功");
    }


    /**
     * 功能描述：注册账号
     *
     * @param userDto 用户信息
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resp register(@RequestBody @Valid UserDto userDto) {
        LOGGER.info("【register】【start】");
        userService.register(userDto);
        LOGGER.info("【register】【end】");
        return new Resp(200, "注册成功");
    }


    /**
     * 功能描述：退出登录
     *
     * @param userDto 用户信息
     */
    @PostMapping(value = "/signout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resp signout(@RequestBody UserDto userDto, HttpSession session) {
        session.invalidate();
        LOGGER.info("【signout】【start】【username:{}】", userDto.getUsername());
        return new Resp(200, "退出成功");
    }


    @GetMapping(value = "/allUser")
    public RespData<List<UserDto>> queryAllUser() {
        LOGGER.info("【queryAllUser】【开始执行】");

        List<UserDto> userDtoList = userService.queryAllUser();
        LOGGER.info("【参数：】【userList:{}】", userDtoList);

        LOGGER.info("【signout】【end】");
        return new RespData<>(userDtoList);
    }


    @GetMapping(value = "/user")
    public String queryUserById(int userId) {
        LOGGER.info("【queryAllUser】【开始执行】");

        UserDto userDto = userService.queryUserById(userId);
        LOGGER.info("【参数：】【userDto:{}】", userDto);

        LOGGER.info("【signout】【end】");
        return "【queryAllUser - Hello World!】";
    }

}
