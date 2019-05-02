package com.kunning.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.kunning.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	@Select("SELECT * FROM user")
    @Results({
        @Result(property = "username",  column = "username", javaType = String.class),
        @Result(property = "nickname", column = "nickname")
    })
    List<User> getAll();

//    @Select("SELECT * FROM users WHERE id = #{id}")
//    @Results({
//        @Result(property = "username",  column = "username", javaType = UserSexEnum.class),
//        @Result(property = "nickname", column = "nickname")
//    })
//    UserEntity getOne(Long id);
//
//    @Insert("INSERT INTO users(username,password,user_sex) VALUES(#{username}, #{password}, #{userSex})")
//    void insert(UserEntity user);
//
//    @Update("UPDATE users SET userName=#{username},nick_name=#{username} WHERE id =#{id}")
//    void update(UserEntity user);
//
//    @Delete("DELETE FROM users WHERE id =#{id}")
//    void delete(Long id);
	
}
