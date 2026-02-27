/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.service;

import com.fengshiqing.springai.constant.PageResult;
import com.fengshiqing.springai.dao.entity.WordFrequency;
import com.fengshiqing.springai.model.dto.WordFrequencyPageQueryDTO;
import com.fengshiqing.springai.dao.WordFrequencyMapper;
import org.apache.commons.lang3.StringUtils;
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


    public PageResult pageQuery(WordFrequencyPageQueryDTO queryDTO) {
        int offset = (queryDTO.getPage() - 1) * queryDTO.getPageSize();
        
        List<WordFrequency> list = wordFrequencyMapper.selectByCondition(
                StringUtils.isNotEmpty(queryDTO.getWord()) ? queryDTO.getWord() : null,
                StringUtils.isNotEmpty(queryDTO.getBusinessType()) ? queryDTO.getBusinessType() : null,
                queryDTO.getCountNumMin(),
                offset,
                queryDTO.getPageSize()
        );
        
        long total = wordFrequencyMapper.countByCondition(
                StringUtils.isNotEmpty(queryDTO.getWord()) ? queryDTO.getWord() : null,
                StringUtils.isNotEmpty(queryDTO.getBusinessType()) ? queryDTO.getBusinessType() : null,
                queryDTO.getCountNumMin()
        );
        
        return new PageResult(total, list);
    }


    public int insert(WordFrequency wordFrequency) {
        return wordFrequencyMapper.insert(wordFrequency);
    }


    public List<WordFrequency> selectAll() {
        return wordFrequencyMapper.selectAll();
    }


    public int insertBatch(List<WordFrequency> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return wordFrequencyMapper.insertBatch(list);
    }


    public int updateBatch(List<WordFrequency> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return wordFrequencyMapper.updateBatch(list);
    }


    public int deleteAll() {
        return wordFrequencyMapper.deleteAll();
    }
}




