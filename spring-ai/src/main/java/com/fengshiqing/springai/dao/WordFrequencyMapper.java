package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.WordFrequency;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 冯仕清
* @description 针对表【word_frequency】的数据库操作Mapper
* @createDate 2025-03-06 15:56:07
* @Entity com.fengshiqing.springai.entity.WordFrequency
*/

@Mapper
public interface WordFrequencyMapper {

    void insert(WordFrequency wordFrequency);

    void deleteById(Integer id);

    WordFrequency selectById(Integer id);

    void update(WordFrequency wordFrequency);
}




