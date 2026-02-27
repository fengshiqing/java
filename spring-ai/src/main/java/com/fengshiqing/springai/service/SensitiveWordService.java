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
 * 功能描述：针对表【sensitive_word】的数据库操作Service实现
 *
* @author 冯仕清
* @since 2025-03-03
*/
@Service
public class SensitiveWordService {

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;


    public int insert(SensitiveWord sensitiveWord) {
        return sensitiveWordMapper.insert(sensitiveWord);
    }


    public SensitiveWord selectById(Integer id) {
        return sensitiveWordMapper.selectById(id.longValue());
    }


    public List<SensitiveWord> selectAll() {
        return sensitiveWordMapper.selectAll();
    }


    public int deleteById(Integer id) {
        return sensitiveWordMapper.deleteById(id.longValue());
    }
}




