package com.kunning.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.kunning.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	@Select("SELECT * FROM user")
    @Results({
        @Result(property = "username", column = "username", javaType = String.class),
        @Result(property = "nickname", column = "nickname")
    })
    List<User> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
        @Result(property = "username", column = "username", javaType = String.class),
        @Result(property = "nickname", column = "nickname")
    })
    User getOne(Long id);

    @Insert("INSERT INTO user(username,password,user_sex) VALUES(#{username}, #{password}, #{userSex})")
    void insert(User user);

    @Update("UPDATE user SET userName=#{username},nick_name=#{username} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

}
