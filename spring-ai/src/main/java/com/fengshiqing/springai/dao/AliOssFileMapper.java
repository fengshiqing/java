package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.AliOssFile;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【ali_oss_file】的数据库操作Mapper
* @createDate 2025-02-08 20:51:33
* @Entity com.Xushu.rag.entity.AliOssFile
*/

@Mapper
public interface AliOssFileMapper {

    @Insert("insert into ali_oss_file(file_name, url, vector_id, create_time, update_time) " +
            "values(#{fileName}, #{url}, #{vectorId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AliOssFile aliOssFile);

    @Select("select * from ali_oss_file where id = #{id}")
    AliOssFile selectById(@Param("id") Long id);

    List<AliOssFile> selectByIds(@Param("ids") List<Long> ids);

    int deleteBatchIds(@Param("ids") List<Long> ids);

    List<AliOssFile> findByFileNameContaining(@Param("fileName") String fileName, 
                                              @Param("offset") Integer offset,
                                              @Param("pageSize") Integer pageSize);

    long countByFileName(@Param("fileName") String fileName);
}




