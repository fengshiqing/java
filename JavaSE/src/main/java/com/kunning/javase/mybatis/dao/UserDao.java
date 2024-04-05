package com.kunning.javase.mybatis.dao;

import com.fengshiqing.common.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
