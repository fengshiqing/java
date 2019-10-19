package com.kunning.javaWeb.service;

import com.kunning.javaWeb.dao.UserDao;
import com.kunning.javaWeb.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    /**
     * <日志>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    // 查询用户信息
    public List<User> queryUser(User user) {
        LOGGER.info("【queryUser】【开始执行】【请求参数：】【user:{}】", user);
        return userDao.queryUser(user);
    }

    public int saveUser(User user) {
        LOGGER.info("【saveUser】【开始执行】【请求参数：】【user:{}】", user);
        return userDao.saveUser(user);
    }

}
