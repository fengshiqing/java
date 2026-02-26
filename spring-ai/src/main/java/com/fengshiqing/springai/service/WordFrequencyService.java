/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.model.resp.RespDataList;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fengshiqing.springai.dao.entity.WordFrequency;
import com.fengshiqing.springai.dao.WordFrequencyMapper;
import com.fengshiqing.springai.model.dto.WordFrequencyPageQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 冯仕清
* @description 针对表【word_frequency】的数据库操作Service实现
* @createDate 2025-03-06 15:56:07
*/
@Service
public class WordFrequencyService {

    @Autowired
    private WordFrequencyMapper wordFrequencyMapper;


    public RespDataList<?> pageQuery(WordFrequencyPageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        // 这里需要根据实际情况实现分页查询，暂时返回空结果
        Page<WordFrequency> page = new Page<>();
        List<WordFrequency> records = List.of();

        return new RespDataList(records, 0);
    }


    public void save(WordFrequency wordFrequency) {
        wordFrequencyMapper.insert(wordFrequency);
    }


    public void update(WordFrequency wordFrequency) {
        wordFrequencyMapper.update(wordFrequency);
    }


    public void delete(Integer id) {
        wordFrequencyMapper.deleteById(id);
    }


    public WordFrequency getById(Integer id) {
        return wordFrequencyMapper.selectById(id);
    }


    public void remove(Object o) {
        // 这里需要实现清空所有数据的方法
        // 由于我们没有在 WordFrequencyMapper 中定义对应的方法，暂时留空
    }


    public List<WordFrequency> list() {
        // 这里需要实现获取所有数据的方法
        // 由于我们没有在 WordFrequencyMapper 中定义对应的方法，暂时返回空列表
        return List.of();
    }
}




