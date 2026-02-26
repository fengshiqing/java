/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.dao.entity.SensitiveWord;
import com.fengshiqing.springai.dao.SensitiveWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_word】的数据库操作Service实现
* @createDate 2025-03-03 21:29:10
*/
@Service
public class SensitiveWordService {

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;


    public void save(SensitiveWord sensitiveWord) {
        sensitiveWordMapper.insert(sensitiveWord);
    }


    public void update(SensitiveWord sensitiveWord) {
        sensitiveWordMapper.update(sensitiveWord);
    }


    public void delete(Integer id) {
        sensitiveWordMapper.deleteById(id);
    }


    public SensitiveWord getById(Integer id) {
        return sensitiveWordMapper.selectById(id);
    }


    public List<SensitiveWord> list() {
        return sensitiveWordMapper.selectAll();
    }
}





