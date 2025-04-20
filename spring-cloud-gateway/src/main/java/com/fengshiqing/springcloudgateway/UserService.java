/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User login(User user) {
        User userDB = new User(1, "123", "冯仕清");
        if(userDB==null){
            throw new RuntimeException("登陆失败!");
        }
        return userDB;
    }

}
