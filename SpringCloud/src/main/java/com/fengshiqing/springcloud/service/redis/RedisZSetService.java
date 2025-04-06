/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.fengshiqing.springcloud.service.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 功能描述：redis服务类
 *
 * @author 冯仕清
 * @since 2019年10月01日
 */
@AllArgsConstructor
@Service
public class RedisZSetService {

    private final RedisTemplate<Object, Object> redisTemplate;


    // =================================================================================================================


    /**
     * 功能描述：有序集合中 添加一个元素
     *
     * @param redisKey redisKey
     * @param value    redisValue
     * @param score    score
     */
    public final void zAdd(String redisKey, Object value, double score) {

        redisTemplate.opsForZSet().add(redisKey, value, score);
    }


    /**
     * 增加 元素的分数(适用于积分累加场景)
     *
     * @param redisKey   redisKey
     * @param elementKey elementKey
     * @param delta      在原有基础上加上 此数字
     */
    public void incrementScore(String redisKey, String elementKey, double delta) {

        redisTemplate.opsForZSet().incrementScore(redisKey, elementKey, delta);
    }

    /**
     * 功能描述：有序集合获取 score 从小到大排序 的集合数据。
     *
     * @param redisKey redisKey
     * @param min      min
     * @param max      max
     * @return Set
     */
    public final Set<Object> rangeByScore(String redisKey, double min, double max) {

        return redisTemplate.opsForZSet().rangeByScore(redisKey, min, max);
    }

    // 添加或更新用户分数
    public void addOrUpdate(String redisKey, String userId, double score) {

        redisTemplate.opsForZSet().add(redisKey, userId, score);
    }


    // 获取用户排名(从高到低)
    public Long getRank(String redisKey, String userId) {

        // 注意: ZSet排名是从0开始，所以+1得到常规排名
        return redisTemplate.opsForZSet().reverseRank(redisKey, userId);
    }


    // 获取zSet中某个元素的分数
    public Double getScore(String redisKey, String bizId) {

        return redisTemplate.opsForZSet().score(redisKey, bizId);
    }


    // 获取排行榜片段（从start到end排名）
    public Set<ZSetOperations.TypedTuple<Object>> getRankingRange(String redisKey, long start, long end) {

        return redisTemplate.opsForZSet().reverseRangeWithScores(redisKey, start, end);
    }


    /**
     * 功能描述：获取排行榜前N名（score从高到低排列的）
     *
     * @param redisKey redisKey
     * @param n        参数N
     * @return 排行榜前N名（score从高到低排列的）
     */
    public Set<ZSetOperations.TypedTuple<Object>> getTopN(String redisKey, int n) {

        return redisTemplate.opsForZSet().reverseRangeWithScores(redisKey, 0, n - 1);
    }


    // 获取排行榜总人数
    public Long getTotalCount(String redisKey) {

        return redisTemplate.opsForZSet().zCard(redisKey);
    }

}
