/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.dao.entity.LogInfo;
import com.fengshiqing.springai.dao.LogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【log_info】的数据库操作Service实现
* @createDate 2025-03-03 21:48:26
*/
@Service
public class LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;


    public int insert(LogInfo logInfo) {
        return logInfoMapper.insert(logInfo);
    }


    public LogInfo selectById(Long id) {
        return logInfoMapper.selectById(id);
    }


    public List<LogInfo> selectAll() {
        return logInfoMapper.selectAll();
    }


    public int deleteById(Long id) {
        return logInfoMapper.deleteById(id);
    }


    public int deleteAll() {
        return logInfoMapper.deleteAll();
    }
}




