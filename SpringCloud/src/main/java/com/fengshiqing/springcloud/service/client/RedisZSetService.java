/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.client;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 功能描述：redis服务类--zSet 类型
 * 已知：有序集合 (zSet) 默认是按照 score 从小到大排序的（升序排列）。
 *
 * @author 冯仕清
 * @since 2019年10月01日
 */
@AllArgsConstructor
@Service
public final class RedisZSetService {

    private final Environment env;

    private final RedisTemplate<String, String> redisTemplate;


    // =================================================================================================================


    /**
     * 功能描述：添加一个元素 到 zSet 中，不存在就新增，存在就更新。
     *
     * @param key redisKey
     * @param elementKey zSet中 某个元素的 key
     * @param score    zSet中 某个元素的 score
     *
     * @return 添加的元素不重复返回true，添加的元素是重复的就返回false。必须是 包装类，应为有可能返回 null
     */
    public Boolean add(String key, String elementKey, double score) {

        return redisTemplate
                .opsForZSet()
                .add(env.getProperty("spring.application.name") + ":" + key, elementKey, score);
    }


    /**
     * 功能描述：增加 元素的分数(适用于积分累加场景)
     * 元素不存在的话，会自动 add 这个元素。
     *
     * @param key        redisKey
     * @param elementKey elementKey
     * @param delta      在原有基础上加上 此数字
     */
    public void incrementScore(String key, String elementKey, double delta) {

        String redisKey = env.getProperty("spring.application.name") + ":" + key;

        redisTemplate.opsForZSet().incrementScore(redisKey, elementKey, delta);

        // 只维护前10000名的精简榜单，剩余的数据保存到数据库中，冷热数据分离，热点数据放入到 redis，冷数据放入到数据库。
        // 这个删除10000 名后的数据，可以在 上一步操作后，发送MQ消息(削峰解耦)，启动单独的任务来更新数据入库，同时清理 缓存中的数据。
        redisTemplate.opsForZSet().removeRange(redisKey, 10000, -1);
    }


    /**
     * 功能描述：获取 redisKey 中 elementKey 元素的分数（就是查看用户的排名）。
     *
     * @param key        redisKey
     * @param elementKey elementKey
     *
     * @return 分数
     */
    @Nullable
    public Double score(String key, String elementKey) {

        return redisTemplate
                .opsForZSet()
                .score(env.getProperty("spring.application.name") + ":" + key, elementKey);
    }


    /**
     * 功能描述：有序集合获取 score 从小到大排序 的集合数据。
     *
     * @param key redisKey
     * @param min min
     * @param max max
     * @return Set
     */
    public Set<String> rangeByScore(String key, double min, double max) {

        return redisTemplate
                .opsForZSet()
                .rangeByScore(env.getProperty("spring.application.name") + ":" + key, min, max);
    }


    /**
     * 功能描述：有序集合获取 score 从小到大排序 的集合数据。
     *
     * @param key redisKey
     * @param min min
     * @param max max
     * @return Set<ZSetOperations.TypedTuple<Object>> 每个元素包含了分数属性
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max) {

        return redisTemplate
                .opsForZSet()
                .rangeByScoreWithScores(env.getProperty("spring.application.name") + ":" + key, min, max);
    }


    // 获取用户排名(从高到低)，注意这个是获取排名的名次，不是 score
    public Long getRank(String key, String elementKey) {

        // 注意: ZSet排名是从0开始，所以+1得到常规排名
        return redisTemplate
                .opsForZSet()
                .reverseRank(env.getProperty("spring.application.name") + ":" + key, elementKey);
    }


    /**
     * 功能描述：获取 指定范围内的 排行榜的分数。
     */
    public Set<String> getRankingScore(String key, long start, long end) {

        return redisTemplate
                .opsForZSet()
                .reverseRange(env.getProperty("spring.application.name") + ":" + key, start, end);
    }


    /**
     * 功能描述：获取排行榜片段（从start到end排名）。例如：取排行榜中 第10 到 20 的名次。
     * 默认顺序是 从小到大，因此需要 reverse 成 从大到小后，再取出
     *
     * @param key  redisKey
     * @param start 从第 start
     * @param end   到第 end
     * @return 排行榜 （从start到end排名）
     */
    public Set<ZSetOperations.TypedTuple<String>> getRankingRange(String key, long start, long end) {

        return redisTemplate
                .opsForZSet()
                .reverseRangeWithScores(env.getProperty("spring.application.name") + ":" + key, start, end);
    }


    /**
     * 功能描述：获取排行榜前N名（score从高到低排列的）
     *
     * @param key redisKey
     * @param n        参数N
     * @return 排行榜前N名（score从高到低排列的）
     */
    public Set<ZSetOperations.TypedTuple<String>> getTopN(String key, int n) {

        return this.getRankingRange(key, 0, n - 1);
    }


    // 获取排行榜总人数
    public Long getTotalCount(String key) {

        return redisTemplate
                .opsForZSet()
                .zCard(env.getProperty("spring.application.name") + ":" + key);
    }




//    private final StringRedisTemplate redisTemplate;
    private static final int BUCKET_COUNT = 16; // 分桶数量，可根据数据量调整


    // 获取分桶key
    private String getBucketKey(String member) {
        int bucket = Math.abs(member.hashCode()) % BUCKET_COUNT;
        return "leaderboard" + ":" + bucket;
    }

    // 添加/更新分数
    public void addScore(String member, double score) {
        String bucketKey = getBucketKey(member);
        redisTemplate.opsForZSet().add(bucketKey, member, score);
    }

    // 获取单个用户排名(从高到低)
    public Long getRank(String member) {
        String bucketKey = getBucketKey(member);

        Long bucketRank = redisTemplate.opsForZSet().reverseRank(bucketKey, member); // 获取用户在桶内的排名
        if (bucketRank == null) return null;
        Double score = redisTemplate.opsForZSet().score(bucketKey, member); // 获取用户的score
        if (score == null) return null;

        // 计算全局排名需要汇总所有更高分数桶的成员数
        long globalRank = bucketRank;
        for (int i = 0; i < BUCKET_COUNT; i++) {
            String currentBucket = "leaderboard:" + i;
            if (!currentBucket.equals(bucketKey)) {
                Long count = redisTemplate.opsForZSet().count(currentBucket, score, Double.MAX_VALUE);
                if (count == null) {
                    continue;
                }
                globalRank += count;
            }
        }

        return globalRank;
    }

    // 获取前N名排行榜
    public List<Map<String, Object>> getTopN(int n) {
        // 使用优先队列合并所有桶的顶部元素
        PriorityQueue<ZSetOperations.TypedTuple<String>> maxHeap = new PriorityQueue<>(
                (o1, o2) -> Double.compare(o2.getScore(), o1.getScore())
        );

        // 从每个桶获取可能的候选者（这里简单获取每个桶前n名）
        for (int i = 0; i < BUCKET_COUNT; i++) {
            String bucketKey = "leaderboard:" + i;
            Set<ZSetOperations.TypedTuple<String>> topEntries =
                    redisTemplate.opsForZSet().reverseRangeWithScores(bucketKey, 0, n - 1);
            if (topEntries != null) {
                maxHeap.addAll(topEntries);
            }
        }

        // 取出全局前n名
        List<Map<String, Object>> result = new ArrayList<>();
        int count = 0;
        while (!maxHeap.isEmpty() && count < n) {
            ZSetOperations.TypedTuple<String> entry = maxHeap.poll();
            Map<String, Object> item = new HashMap<>();
            item.put("member", entry.getValue());
            item.put("score", entry.getScore());
            result.add(item);
            count++;
        }

        return result;
    }


}
