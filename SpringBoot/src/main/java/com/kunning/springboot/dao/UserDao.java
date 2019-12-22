package com.kunning.springboot.dao;

import java.util.List;

import com.kunning.springboot.pojo.User;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int insert(User user);

    User queryUserById(int id);

    List<User> queryAllUser();

    void update(User user);

    void delete(Long id);

}
