/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fengshiqing.springcloudgateway.JWTUitls;
import com.fengshiqing.springcloudgateway.User;
import com.fengshiqing.springcloudgateway.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@RestController
public class UserController {

    private final UserService userService;


    @GetMapping("/user/login")
    public Map<String, Object> login(@RequestParam("name") String username, @RequestParam("pwd") String password) {
        Map<String, Object> result = new HashMap<>();

        User user = new User(0, username, password);

        try {
            User userDB = userService.login(user); // 在数据库中查询是否用户名存在

            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId().toString());
            payload.put("username", userDB.getUsername());

            // 生成JWT令牌，https://jwt.io/ 这里可以看token的组成部分
            String token = JWTUitls.getToken(payload);

            result.put("state", true);
            result.put("msg", "登陆成功!");
            result.put("token", token);
        } catch (Exception e) {
            log.error("【login】【happened Exception：】", e);
            result.put("state", false);
            result.put("msg", "登陆异常!");
        }

        return result;
    }

    /**
     * 一个token用户授权的功能接口
     *
     * @param request 请求
     * @return 响应
     */
    @RequestMapping("/user/test")
    public Map<String, Object> test(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();

        // 处理自己的逻辑业务
        // 此时我们想要获取 token中的用户信息，token经过拦截器拦截绝对是正确的
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUitls.getTokenInfo(token);
        User user = new User(Integer.parseInt(tokenInfo.getClaim("id").asString()), tokenInfo.getClaim("username").asString(), null);

        // 返回用户的相关信息的map集合
        map.put("data", user);
        map.put("state", true);
        map.put("msg", "请求成功!");

        return map;
    }

}
