/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.dao.entity.SensitiveCategory;
import com.fengshiqing.springai.dao.SensitiveCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_category】的数据库操作Service实现
* @createDate 2025-03-17 20:59:17
*/
@Service
public class SensitiveCategoryService {

    @Autowired
    private SensitiveCategoryMapper sensitiveCategoryMapper;


    public int insert(SensitiveCategory sensitiveCategory) {
        return sensitiveCategoryMapper.insert(sensitiveCategory);
    }


    public SensitiveCategory selectById(Integer id) {
        return sensitiveCategoryMapper.selectById(id.longValue());
    }


    public List<SensitiveCategory> selectAll() {
        return sensitiveCategoryMapper.selectAll();
    }


    public int deleteById(Integer id) {
        return sensitiveCategoryMapper.deleteById(id.longValue());
    }
}




