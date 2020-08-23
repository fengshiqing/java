/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：redis服务类
 *
 * @author 冯仕清
 * @since 2019年10月01日
 */
@Service
public class RedisService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // =================================================================================================================

    /**
     * 功能描述：判断是否存在
     *
     * @param key redisKey
     *
     * @return true存在，false不存在
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 功能描述：删除redis缓存
     *
     * @param key redisKey
     */
    public void delete(final String key) {
        redisTemplate.delete(key);
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
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
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
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 功能描述：写入字符串类型缓存
     *
     * @param key   redisKey
     * @param value redisValue
     */
    public void set(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 功能描述：写入字符串类型缓存。可以设置过期时间
     *
     * @param key        redisKey
     * @param value      redisValue
     * @param expireTime 过期时间，单位/秒
     */
    public void set(final String key, Object value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }

    // =================================================================================================================

    /**
     * 功能描述：写入Map(哈希)类型的数据
     *
     * @param key     redisKey
     * @param hashKey hashKey
     * @param value   redisValue
     */
    public void hmSet(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 功能描述：获取Map(哈希)类型的数据
     *
     * @param key     redisKey
     * @param hashKey
     *
     * @return Map(哈希)类型的数据
     */
    public Object hmGet(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    // =================================================================================================================

    /**
     * 功能描述：写入List(列表)类型的数据
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        redisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 功能描述：读取List(列表)类型的数据
     *
     * @param k
     * @param l
     * @param l1
     *
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        return redisTemplate.opsForList().range(k, l, l1);
    }

    // =================================================================================================================

    /**
     * 功能描述：集合添加
     *
     * @param key   redisKey
     * @param value redisValue
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 功能描述：集合获取
     *
     * @param key redisKey
     *
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    // =================================================================================================================

    /**
     * 功能描述：有序集合添加
     *
     * @param key    redisKey
     * @param value  redisValue
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 功能描述：有序集合获取
     *
     * @param key     redisKey
     * @param scoure
     * @param scoure1
     *
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }
}
