package com.fengshiqing.springai.dao;

import com.fengshiqing.springai.dao.entity.WordFrequency;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author 冯仕清
* @description 针对表【word_frequency】的数据库操作Mapper
* @createDate 2025-03-06 15:56:07
* @Entity com.Xushu.rag.entity.WordFrequency
*/

@Mapper
public interface WordFrequencyMapper {

    @Insert("insert into word_frequency(word, count_num, business_type, create_time, update_time) " +
            "values(#{word}, #{countNum}, #{businessType}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(WordFrequency wordFrequency);

    @Update("update word_frequency set word=#{word}, count_num=#{countNum}, business_type=#{businessType}, " +
            "create_time=#{createTime}, update_time=#{updateTime} where id=#{id}")
    int update(WordFrequency wordFrequency);

    @Select("select * from word_frequency where id = #{id}")
    WordFrequency selectById(@Param("id") Integer id);

    @Select("select * from word_frequency")
    List<WordFrequency> selectAll();

    @Delete("delete from word_frequency where id = #{id}")
    int deleteById(@Param("id") Integer id);

    @Delete("delete from word_frequency")
    int deleteAll();

    int insertBatch(@Param("list") List<WordFrequency> list);

    int updateBatch(@Param("list") List<WordFrequency> list);

    List<WordFrequency> selectByCondition(@Param("word") String word, 
                                          @Param("businessType") String businessType, 
                                          @Param("countNumMin") Integer countNumMin,
                                          @Param("offset") Integer offset,
                                          @Param("pageSize") Integer pageSize);

    long countByCondition(@Param("word") String word, 
                         @Param("businessType") String businessType, 
                         @Param("countNumMin") Integer countNumMin);
}




