/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service;

import com.fengshiqing.springcloud.constant.RankingConst;
import com.fengshiqing.springcloud.mapper.RankingMapper;
import com.fengshiqing.springcloud.mapper.entity.RankingEntity;
import com.fengshiqing.springcloud.service.dto.RankingDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 功能描述：排行榜服务。
 *
 * @author fengshiqing
 * @since 2023-09-09
 */
@Slf4j
@AllArgsConstructor
@Service
public class RankingService {

    private final RankingCommonService rankingCommonService;

    private final RankingMapper rankingMapper;


    /**
     * 功能描述：刷新榜单。
     *
     * @param rankType 榜单类型
     * @param bizId 排行榜中的业务对象的ID，例如 帖子的ID、文章的ID
     */
    public void updateRanking(String rankType, String bizId) {
        log.info("【updateRanking】【start】【rankType：{}，bizId：{}】", rankType, bizId);

        // 1、校验用户有效性，防止有人恶意刷榜单

        // 2、更新榜单
        switch (rankType) { // 不同的榜单类型，需要校验，比如：每个人对同一个业务对象只能点赞1次、收藏1次、转发1+次，评论1+次
            case RankingConst.RankingType.like:
                // TODO 每个人只能点赞喜欢一次
                break;

            case RankingConst.RankingType.collect:
                break;

            case RankingConst.RankingType.transmit:
                break;

            case RankingConst.RankingType.discuss:
                break;

            default:
                break;

        }

        int count = rankingMapper.insertRankingHistory(new RankingEntity(rankType, bizId, 1));
        log.info("【updateRanking】【end】【count:{}】", count);
    }


    /**
     * 功能描述：查询榜单。
     *
     * @param rankType 榜单类型
     */
    public List<RankingDto> selectAndCalcRanking(String rankType) {

        Set<ZSetOperations.TypedTuple<Object>> topN = rankingCommonService.getTopN(rankType, 100);

        List<RankingDto> rankingDtoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(topN)) {
            for (ZSetOperations.TypedTuple<Object> typedTuple : topN) {
                RankingDto rankingEntity = new RankingDto((String) typedTuple.getValue(), String.valueOf(typedTuple.getScore()));
                rankingDtoList.add(rankingEntity);
            }
            return rankingDtoList;
        }

        List<RankingEntity> rankingEntityList = rankingMapper.selectRankingByType(rankType);
        for (RankingEntity rankingEntity : rankingEntityList) {
            RankingDto rankingDto = new RankingDto(rankingEntity.getBizId(), String.valueOf(rankingEntity.getTotalQuantity()));
            rankingDtoList.add(rankingDto);
        }

        return rankingDtoList;
    }




}
