package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.LogInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 冯仕清
* @description 针对表【log_info】的数据库操作Mapper
* @createDate 2025-03-03 21:48:26
* @Entity com.fengshiqing.springai.entity.LogInfo
*/

@Mapper
public interface LogInfoMapper {

    void insert(LogInfo logInfo);

    void deleteById(Integer id);

    LogInfo selectById(Integer id);

    void update(LogInfo logInfo);
}




