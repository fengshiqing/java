/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * 功能描述：redis服务类
 *
 * @author 冯仕清
 * @since 2019年10月01日
 */
@AllArgsConstructor
@Service
public class RedisService {

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
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 功能描述：删除redis缓存
     *
     * @param key redisKey
     */
    public void delete(final String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 功能描述：批量删除对应的value
     *
     * @param keys redisKey
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 功能描述：批量删除key
     *
     * @param pattern 前缀
     */
    public final void removePattern(final String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        if (keys != null && keys.size() > 0)
            redisTemplate.delete(keys);
    }

    // =================================================================================================================

    /**
     * 功能描述：读取字符串类型缓存
     *
     * @param key redisKey
     *
     * @return redisValue
     */
    public final Object get(final String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 功能描述：写入字符串类型缓存
     *
     * @param key redisKey
     * @param value redisValue
     */
    public final void set(final String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 功能描述：写入字符串类型缓存。可以设置过期时间
     *
     * @param key redisKey
     * @param value redisValue
     * @param expireTime 过期时间，单位/秒
     */
    public final void set(final String key, String value, Long expireTime) {
        stringRedisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    // =================================================================================================================

    /**
     * 功能描述：写入Map(哈希)类型的数据
     *
     * @param key redisKey
     * @param hashKey hashKey
     * @param value redisValue
     */
    public final void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
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
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    // =================================================================================================================

    /**
     * 功能描述：写入List(列表)类型的数据
     *
     * @param k key
     * @param v value
     */
    public final void lPush(String k, Object v) {
        redisTemplate.opsForList().rightPush(k, v);
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
        return redisTemplate.opsForList().range(key, start, end);
    }

    // =================================================================================================================

    /**
     * 功能描述：集合添加
     *
     * @param key redisKey
     * @param value redisValue
     */
    public final void add(String key, Object value) {
        SetOperations<Object, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
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
        return set.members(key);
    }

    // =================================================================================================================

    /**
     * 功能描述：有序集合添加
     *
     * @param key redisKey
     * @param value redisValue
     * @param score score
     */
    public final void zAdd(String key, Object value, double score) {
        ZSetOperations<Object, Object> zSet = redisTemplate.opsForZSet();
        zSet.add(key, value, score);
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
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }
}
