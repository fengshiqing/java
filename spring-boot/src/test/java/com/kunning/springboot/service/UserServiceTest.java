/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springboot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.kunning.springboot.MyApplication;
import com.kunning.springboot.model.UserDto;

@SpringJUnitConfig
@SpringBootTest(classes = MyApplication.class)
class UserServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public UserDto queryUserInfoByusername() {
        String username = "zhangsan";
        String sql = "SELECT username, password FROM t_user WHERE username = ?";
        List<UserDto> userList = jdbcTemplate.query(sql, new Object[] { UserDto.class }, new BeanPropertyRowMapper<>());
        if (userList.size() == 1) {
            return userList.get(0);
        }
        UserDto userDto = jdbcTemplate.queryForObject(sql, new Object[] { username },
                new BeanPropertyRowMapper<>(UserDto.class));
        return null;
    }

    @Test
    void queryAllUser() {
    }

    @Test
    void loadUserByUsername() {
    }

}