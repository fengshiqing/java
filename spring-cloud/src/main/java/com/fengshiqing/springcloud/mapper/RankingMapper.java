/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.mapper;

import com.fengshiqing.springcloud.mapper.entity.RankingEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：排行榜 Mapper
 *
 * @since 2025-04-04
 * @author fengshiqing
 */
@Repository
public interface RankingMapper {

    int insertRankingHistory(RankingEntity entity);


    List<RankingEntity> selectRankingByType(@Param("rankType") String rankType);


    RankingEntity selectRankingByBizId(@Param("rankType") String rankType, @Param("bizId") String bizId);


    // =================================================================================================================


    int insertRankingResult(RankingEntity entity);


    List<RankingEntity> selectRankingResult(@Param("rankType") String rankType);

}