package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.SensitiveWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_word】的数据库操作Mapper
* @createDate 2025-03-03 21:29:10
* @Entity com.fengshiqing.springai.entity.SensitiveWord
*/

@Mapper
public interface SensitiveWordMapper {

    void insert(SensitiveWord sensitiveWord);

    void deleteById(Integer id);

    SensitiveWord selectById(Integer id);

    void update(SensitiveWord sensitiveWord);

    List<SensitiveWord> selectAll();
}




