/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service;

import com.fengshiqing.springcloud.mapper.RankingMapper;
import com.fengshiqing.springcloud.mapper.entity.RankingEntity;
import com.fengshiqing.springcloud.service.client.RedisZSetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 功能描述：排行榜 的公共方法服务。
 *
 * @author fengshiqing
 * @since 2023-09-09
 */
@Slf4j
@AllArgsConstructor
@Service
public class RankingCommonService {

    private final RedisZSetService redisZSetService;

    private final RankingMapper rankingMapper;

    private static final String REDIS_TYPE = "ranking:";


    /**
     * 功能描述：刷新榜单。
     *
     * @param rankType 榜单类型
     * @param bizId 排行榜中的业务对象的ID，例如 帖子的ID、文章的ID
     */
    @Async("commonThreadPool")
    public void updateRankingToRedis(String rankType, String bizId) {
        log.info("【updateRankingToRedis】【start】【rankType：{}，bizId：{}】", rankType, bizId);

        // 1、查询数据库中的数据
        RankingEntity rankingEntity = rankingMapper.selectRankingByBizId(rankType, bizId);

        // 2、更新到redis中的榜单
        redisZSetService.incrementScore(REDIS_TYPE + rankType, bizId, 1);

        log.info("【updateRankingToRedis】【end】");
    }


    /**
     * 功能描述：查询榜单的 TopN。
     *
     * @param rankType 榜单类型
     */
    public Set<ZSetOperations.TypedTuple<Object>> getTopN(String rankType, int n) {
        return redisZSetService.getTopN(REDIS_TYPE + rankType, n);
    }

}
