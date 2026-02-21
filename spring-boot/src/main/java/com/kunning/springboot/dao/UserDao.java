package com.kunning.springboot.dao;

import com.kunning.springboot.model.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int insert(UserDto userDto);

    int update(UserDto userDto);

    int delete(Long id);

    UserDto queryUserById(int id);

    UserDto queryUserByUserName(String username);

    List<UserDto> queryAllUser();

}
