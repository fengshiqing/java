package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.LogInfo;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【log_info】的数据库操作Mapper
* @createDate 2025-03-03 21:48:26
* @Entity com.Xushu.rag.entity.LogInfo
*/

@Mapper
public interface LogInfoMapper {

    @Insert("insert into log_info(method_name, class_name, request_time, request_params, response) " +
            "values(#{methodName}, #{className}, #{requestTime}, #{requestParams}, #{response})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(LogInfo logInfo);

    @Select("select * from log_info where id = #{id}")
    LogInfo selectById(@Param("id") Long id);

    @Select("select * from log_info")
    List<LogInfo> selectAll();

    @Delete("delete from log_info where id = #{id}")
    int deleteById(@Param("id") Long id);

    @Delete("delete from log_info")
    int deleteAll();

    List<LogInfo> selectByCondition(@Param("methodName") String methodName, 
                                     @Param("className") String className, 
                                     @Param("requestParams") String requestParams,
                                     @Param("offset") Integer offset,
                                     @Param("pageSize") Integer pageSize);

    long countByCondition(@Param("methodName") String methodName, 
                         @Param("className") String className, 
                         @Param("requestParams") String requestParams);
}




