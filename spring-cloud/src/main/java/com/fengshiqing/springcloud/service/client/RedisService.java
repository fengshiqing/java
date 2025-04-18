/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.client;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：redis服务类
 *
 * @author 冯仕清
 * @since 2019年10月01日
 */
@AllArgsConstructor
@Service
public class RedisService {

    private final Environment env;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;


    // =================================================================================================================

    /**
     * 功能描述：判断是否存在
     *
     * @param key redisKey
     *
     * @return true存在，false不存在
     */
    public Boolean exists(final String key) {
        return stringRedisTemplate.hasKey(env.getProperty("spring.application.name") + ":" + key);
    }

    /**
     * 功能描述：删除redis缓存
     *
     * @param key redisKey
     */
    public void delete(final String key) {
        stringRedisTemplate.delete(env.getProperty("spring.application.name") + ":" + key);
    }

    /**
     * 功能描述：批量删除对应的value
     *
     * @param keys redisKey
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(env.getProperty("spring.application.name") + ":" + key);
        }
    }

    /**
     * 功能描述：批量删除key
     *
     * @param pattern 前缀
     */
    public final void removePattern(final String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    // ======================================================String=====================================================

    /**
     * 功能描述：读取字符串类型缓存
     *
     * @param key redisKey
     *
     * @return redisValue
     */
    public final Object get(final String key) {
        return stringRedisTemplate.opsForValue().get(env.getProperty("spring.application.name") + ":" + key);
    }

    /**
     * 功能描述：写入字符串类型缓存
     *
     * @param key redisKey
     * @param value redisValue
     */
    public final void set(final String key, String value) {
        stringRedisTemplate.opsForValue().set(env.getProperty("spring.application.name") + ":" + key, value);
    }

    /**
     * 功能描述：写入字符串类型缓存。可以设置过期时间
     *
     * @param key redisKey
     * @param value redisValue
     * @param expireTime 过期时间，单位/秒
     */
    public final void set(final String key, String value, Long expireTime) {
        stringRedisTemplate.opsForValue().set(env.getProperty("spring.application.name") + ":" + key, value, expireTime, TimeUnit.SECONDS);
    }

    // ======================================================Map========================================================

    /**
     * 功能描述：写入Map(哈希)类型的数据
     *
     * @param key redisKey
     * @param hashKey hashKey
     * @param value redisValue
     */
    public final void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(env.getProperty("spring.application.name") + ":" + key, hashKey, value);
    }

    /**
     * 功能描述：获取Map(哈希)类型的数据
     *
     * @param key redisKey
     * @param hashKey hashKey
     *
     * @return Map(哈希)类型的数据
     */
    public final Object hmGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(env.getProperty("spring.application.name") + ":" + key, hashKey);
    }

    // ======================================================List=======================================================

    /**
     * 功能描述：写入List(列表)类型的数据
     *
     * @param k key
     * @param v value
     */
    public final void lPush(String k, Object v) {
        redisTemplate.opsForList().rightPush(env.getProperty("spring.application.name") + ":" + k, v);
    }

    /**
     * 功能描述：读取List(列表)类型的数据
     *
     * @param key key
     * @param start start
     * @param end end
     *
     * @return List
     */
    public final List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(env.getProperty("spring.application.name") + ":" + key, start, end);
    }

    // ======================================================Set========================================================

    /**
     * 功能描述：集合添加
     *
     * @param key redisKey
     * @param value redisValue
     */
    public final void add(String key, Object value) {
        SetOperations<Object, Object> set = redisTemplate.opsForSet();
        set.add(env.getProperty("spring.application.name") + ":" + key, value);
    }

    /**
     * 功能描述：集合获取
     *
     * @param key redisKey
     *
     * @return Set
     */
    public final Set<Object> setMembers(String key) {
        SetOperations<Object, Object> set = redisTemplate.opsForSet();
        return set.members(env.getProperty("spring.application.name") + ":" + key);
    }


    // ======================================================zSet=======================================================


    /**
     * 功能描述：有序集合添加
     *
     * @param key redisKey
     * @param value redisValue
     * @param score score
     */
    public final void zAdd(String key, Object value, double score) {
        ZSetOperations<Object, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(env.getProperty("spring.application.name") + ":" + key, value, score);
    }

    /**
     * 功能描述：有序集合获取
     *
     * @param key redisKey
     * @param min min
     * @param max max
     *
     * @return Set
     */
    public final Set<Object> rangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(env.getProperty("spring.application.name") + ":" + key, min, max);
    }

    // 添加或更新用户分数
    public void addOrUpdate(String key, String userId, double score) {
        redisTemplate.opsForZSet().add(env.getProperty("spring.application.name") + ":" + key, userId, score);
    }

    // 增加用户分数(适用于积分累加场景)
    public void incrementScore(String key, String userId, double delta) {
        redisTemplate.opsForZSet().incrementScore(env.getProperty("spring.application.name") + ":" + key, userId, delta);
    }

    // 获取用户排名(从高到低)
    public Long getRank(String key, String userId) {
        // 注意: ZSet排名是从0开始，所以+1得到常规排名
        return redisTemplate.opsForZSet().reverseRank(env.getProperty("spring.application.name") + ":" + key, userId);
    }

    // 获取用户分数
    public Double getScore(String key, String userId) {
        return redisTemplate.opsForZSet().score(env.getProperty("spring.application.name") + ":" + key, userId);
    }

    // 获取排行榜前N名(从高到低)
    public Set<ZSetOperations.TypedTuple<Object>> getTopN(String key, int n) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(env.getProperty("spring.application.name") + ":" + key, 0, n - 1);
    }

    // 获取排行榜片段(从start到end排名)
    public Set<ZSetOperations.TypedTuple<Object>> getRankingRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(env.getProperty("spring.application.name") + ":" + key, start, end);
    }

    // 获取排行榜总人数
    public Long getTotalCount(String key) {
        return redisTemplate.opsForZSet().zCard(env.getProperty("spring.application.name") + ":" + key);
    }
}
