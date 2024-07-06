/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.common.bean;

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
public class User {

    /**
     * ID
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别：0保密、1男性、2女性
     */
    private int gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    public static void main(String[] args) {
        // 手机号脱敏：176****9843，说明：https://blog.csdn.net/BjarneCpp/article/details/79107873

        System.out.println(("17602509843".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2")));
    }
}