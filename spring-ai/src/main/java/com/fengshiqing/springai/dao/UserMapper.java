package com.fengshiqing.springai.dao;

import com.github.pagehelper.Page;
import com.fengshiqing.springai.dao.entity.User;
import com.fengshiqing.springai.model.dto.UserPageQueryDTO;
import org.apache.ibatis.annotations.*;

/**
* @author 冯仕清
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-02-14 21:05:04
* @Entity com.Xushu.rag.entity.User
*/
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where user_name = #{userName}")
    User getByUsername(@Param("userName") String userName);

    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);

    void updateUser(User user);

    @Insert("insert into tb_user(name, user_name, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values(#{name}, #{userName}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Select("select * from tb_user where id = #{id}")
    User selectById(@Param("id") Integer id);

    @Delete("delete from tb_user where id = #{id}")
    int deleteById(@Param("id") Integer id);
}




