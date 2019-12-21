package com.kunning.springboot.service;

import com.kunning.springboot.controller.LoginController;
import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserDao userDao;



    /**
     * 功能描述：登录
     *
     * @param user 用户信息
     */
    public int insert(User user) {
        Objects.requireNonNull(user, "【user不能为null】");
        return userDao.insert(user);
    }

    /**
     * 功能描述：登录
     *
     * @param user 用户信息
     */
    public User register(User user) {
        userDao.queryAllUser();
        return new User();

    }

}
