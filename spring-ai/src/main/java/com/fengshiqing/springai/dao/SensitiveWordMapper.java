package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.SensitiveWord;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_word】的数据库操作Mapper
* @createDate 2025-03-03 21:29:10
* @Entity com.Xushu.rag.entity.SensitiveWord
*/

@Mapper
public interface SensitiveWordMapper {

    @Insert("insert into sensitive_word(word, category_id, create_time, update_time) " +
            "values(#{word}, #{categoryId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SensitiveWord sensitiveWord);

    @Select("select * from sensitive_word where id = #{id}")
    SensitiveWord selectById(@Param("id") Long id);

    @Select("select * from sensitive_word")
    List<SensitiveWord> selectAll();

    @Delete("delete from sensitive_word where id = #{id}")
    int deleteById(@Param("id") Long id);

    List<SensitiveWord> selectByCondition(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    long countAll();
}




