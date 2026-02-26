package com.fengshiqing.springai.service;

import com.fengshiqing.springai.constant.PageResult;
import com.fengshiqing.springai.dao.entity.User;
import com.fengshiqing.springai.model.dto.UserDTO;
import com.fengshiqing.springai.model.dto.UserPageQueryDTO;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;

/**
* @author 冯仕清
* @description 针对表【user】的数据库操作Service
* @createDate 2025-02-14 21:05:04
*/
public interface UserService {

    User login(String userName, String password) throws AccountNotFoundException, AccountLockedException;

    void saveUser(UserDTO userDTO);

    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    void startOrStop(Integer status, Integer id);

    void updateUser(UserDTO userDTO);

    void register(User user);

    boolean getByUsername(String userName);

    void delete(Integer id);

    User getById(int id);

    void updateById(User user);
}
