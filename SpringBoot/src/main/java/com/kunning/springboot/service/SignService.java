package com.kunning.springboot.service;

import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SignService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SignService.class);

    @Autowired
    private UserDao userDao;

    /**
     * 功能描述：登录
     *
     * @param userDto 用户信息
     */
    public int insert(UserDto userDto) {
        Objects.requireNonNull(userDto, "【user不能为null】");
        return userDao.insert(userDto);
    }

    /**
     * 功能描述：登录
     *
     * @param userDto 用户信息
     */
    public UserDto register(UserDto userDto) {
        userDao.queryAllUser();
        return new UserDto();

    }

}
