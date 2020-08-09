package com.kunning.springboot.service;

import com.kunning.springboot.Application;
import com.kunning.springboot.pojo.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class UserServiceTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public UserDto queryUserInfoByusername() {
        String username = "zhangsan";
        String sql = "SELECT username, password FROM sys_user WHERE username = ?";
        List<UserDto> userList = jdbcTemplate.query(sql, new Object[]{UserDto.class}, new BeanPropertyRowMapper<>());
        if (userList.size() == 1) {
            return userList.get(0);
        }
        UserDto userDto = jdbcTemplate.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        return null;
    }

    @Test
    void queryAllUser() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void bcr() {
        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
    }

}