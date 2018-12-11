package com.mybatis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mybatis.dao.IUserDao;
import com.mybatis.pojo.User;
import com.mybatis.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;
    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }
}
