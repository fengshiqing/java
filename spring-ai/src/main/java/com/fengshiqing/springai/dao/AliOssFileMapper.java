package com.fengshiqing.springai.dao;

import com.github.pagehelper.Page;
import com.fengshiqing.springai.dao.entity.AliOssFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 冯仕清
* @description 针对表【ali_oss_file】的数据库操作Mapper
* @createDate 2025-02-08 20:51:33
* @Entity com.fengshiqing.springai.entity.AliOssFile
*/

@Mapper
public interface AliOssFileMapper {

    Page<AliOssFile> findByFileNameContaining(@Param("fileName") String fileName);

    void insert(AliOssFile aliOssFile);

    void deleteById(Integer id);

    AliOssFile selectById(Integer id);

    void update(AliOssFile aliOssFile);
}




