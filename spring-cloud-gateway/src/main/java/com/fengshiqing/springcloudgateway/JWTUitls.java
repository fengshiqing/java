/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUitls {
    private static final String SECRET = "!@#$%^&";//在真实开发中密钥更严谨，一定不要泄露

    /**
     * 生成token，得有header、payload、signature
     * 默认7天过期
     * 使用 HMAC256加密
     * 通过map传递 payload 中的有效载荷
     */
    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 7);// 设置一个7天的时间段
        calendar.add(Calendar.MINUTE, 7);// 设置一个7分钟的时间段

        JWTCreator.Builder builder = JWT.create();

        // payload
        for (Map.Entry<String, String> entry : map.entrySet()) {// 设置自定义的键值对
            builder.withClaim(entry.getKey(), entry.getValue());
        }

        // 设置过期时间， 并使用加密算法设置盐值，最终 生成/计算出 jwt签名
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token) {
        // 验证 token
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        // 使用JWT的require，生成一个验证对象

    }

    /**
     * 解析token中的内容,先验证token合法性饭后返回token解码的对象
     */
    public static DecodedJWT getTokenInfo(String token) {
        // 使用JWT的require，生成一个验证对象
        // 获取token信息的同时会对token做校验，如果有问题的话，那么抛出各种异常
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

}
