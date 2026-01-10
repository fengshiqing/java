/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：用户信息
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@Getter
@Setter
@ToString
public class UserEntity {

    /**
     * ID
     */
    private long id;

    /**
     * ID
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别：0保密、1男性、2女性
     */
    private int gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 个性签名
     */
    private String personalizedSignature;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 上一次登录时间
     */
    private String lastLoginTime;

    public static void main(String[] args) {
        // 手机号脱敏：176****9843，说明：https://blog.csdn.net/BjarneCpp/article/details/79107873

        System.out.println(("17602509843".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")));
    }
}