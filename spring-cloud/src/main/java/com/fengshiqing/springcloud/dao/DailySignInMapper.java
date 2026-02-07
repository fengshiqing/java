/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.dao;

import com.fengshiqing.springcloud.dao.entity.DailySignEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 功能描述：每日签到 Mapper
 *
 * @since 2023-11-09
 * @author 冯仕清
 */
@Repository
public interface DailySignInMapper {

    int insert(DailySignEntity entity);


    int updateByPrimaryKey(DailySignEntity entity);


    DailySignEntity selectByPrimaryKey(@Param("userId") String userId, @Param("month") int month);

}