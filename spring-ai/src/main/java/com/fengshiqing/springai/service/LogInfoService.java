/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.dao.entity.LogInfo;
import com.fengshiqing.springai.dao.LogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 冯仕清
* @description 针对表【log_info】的数据库操作Service实现
* @createDate 2025-03-03 21:48:26
*/
@Service
public class LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;


    public void save(LogInfo logInfo) {
        logInfoMapper.insert(logInfo);
    }


    public void update(LogInfo logInfo) {
        logInfoMapper.update(logInfo);
    }


    public void delete(Integer id) {
        logInfoMapper.deleteById(id);
    }


    public LogInfo getById(Integer id) {
        return logInfoMapper.selectById(id);
    }
}





