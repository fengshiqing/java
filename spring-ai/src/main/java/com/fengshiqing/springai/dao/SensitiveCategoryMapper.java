package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.SensitiveCategory;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【sensitive_category】的数据库操作Mapper
* @createDate 2025-03-17 20:59:17
* @Entity com.Xushu.rag.entity.SensitiveCategory
*/

@Mapper
public interface SensitiveCategoryMapper {

    @Insert("insert into sensitive_category(name, description, create_time, update_time) " +
            "values(#{name}, #{description}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SensitiveCategory sensitiveCategory);

    @Select("select * from sensitive_category where id = #{id}")
    SensitiveCategory selectById(@Param("id") Long id);

    @Select("select * from sensitive_category")
    List<SensitiveCategory> selectAll();

    @Delete("delete from sensitive_category where id = #{id}")
    int deleteById(@Param("id") Long id);

    List<SensitiveCategory> selectByCondition(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    long countAll();
}




