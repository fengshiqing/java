package com.kunning.springboot.dao;

import java.util.List;

import com.kunning.springboot.pojo.UserDto;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int insert(UserDto userDto);

    int update(UserDto userDto);

    int delete(Long id);

    UserDto queryUserById(int id);

    UserDto queryUserByUserName(String username);

    List<UserDto> queryAllUser();

}
