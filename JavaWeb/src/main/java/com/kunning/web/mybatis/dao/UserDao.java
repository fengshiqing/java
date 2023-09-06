package com.kunning.web.mybatis.dao;

import java.util.List;

import com.fengshiqing.common.bean.User;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    // 注解sql和xml的sql语句不能同时存在，否则会报错。
//	@Select("SELECT * FROM user")
//    @Results({
//        @Result(property = "username", column = "username", javaType = String.class),
//        @Result(property = "nickname", column = "nickname")
//    })
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
