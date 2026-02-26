package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.SensitiveCategory;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_category】的数据库操作Mapper
* @createDate 2025-03-17 20:59:17
* @Entity com.fengshiqing.springai.entity.SensitiveCategory
*/

@Mapper
public interface SensitiveCategoryMapper {

    void insert(SensitiveCategory sensitiveCategory);

    void deleteById(Integer id);

    SensitiveCategory selectById(Integer id);

    void update(SensitiveCategory sensitiveCategory);

    List<SensitiveCategory> selectAll();
}




