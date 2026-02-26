package com.fengshiqing.springai.dao;

import com.github.pagehelper.Page;
import com.fengshiqing.springai.dao.entity.User;
import com.fengshiqing.springai.model.dto.UserPageQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
* @author 冯仕清
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-02-14 21:05:04
* @Entity com.fengshiqing.springai.entity.User
*/
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where user_name = #{userName}")
    User getByUsername(@Param("userName") String userName);

    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);

    void updateUser(User user);

    void insert(User user);

    void deleteById(Integer id);

    User selectById(Integer id);
}




