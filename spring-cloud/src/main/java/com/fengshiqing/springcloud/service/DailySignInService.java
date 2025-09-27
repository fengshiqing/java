/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service;

import com.fengshiqing.common.BizException;
import com.fengshiqing.springcloud.dao.DailySignInMapper;
import com.fengshiqing.springcloud.dao.entity.DailySignEntity;
import com.fengshiqing.springcloud.service.dto.DailySignVo;
import com.fengshiqing.springcloud.utils.I18nUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 功能描述：签到服务
 *
 * @author fengshiqing
 * @since 2023-09-09
 */
@Slf4j
@AllArgsConstructor
@Service
public class DailySignInService {

    private final DailySignInMapper dailySignInMapper;


    /**
     * 功能描述：每日签到。
     * TODO 分布式锁，防止多个地方同时签到，造成数据相互覆盖。
     *
     * @param dailySignVo 入参
     */
    public void dailySignIn(DailySignVo dailySignVo) {
        log.info("【dailySignIn】【start】【dailySignVo：{}】", dailySignVo);

        String userId =dailySignVo.getUserId();
        int dayOfMonth = dailySignVo.getDayOfMonth();
        int month = dailySignVo.getMonth();

        // 1、判断签到的日期是否合法
        this.checkDate(month, dayOfMonth);

        // 2、查询签到记录
        DailySignEntity dailySignEntity = dailySignInMapper.selectByPrimaryKey(userId, month);

        // 3.1、本月首次签到
        DailySignEntity newEntity = new DailySignEntity(userId, month);
        if (dailySignEntity == null) {
            newEntity.setDetail(1 << (dayOfMonth - 1));
            newEntity.setTotalNum((byte) 1); // 第一次签到时，当月签到总次数为1
            newEntity.setContinuousNum((byte) 1); // 第一次签到时，当月最长连续签到次数为1
            dailySignInMapper.insert(newEntity);
        } else {
            // 3.2、本月非首次签到。
            String oldDailySignDetail = Integer.toBinaryString(dailySignEntity.getDetail());
            if (oldDailySignDetail.length() >= dayOfMonth && oldDailySignDetail.charAt(oldDailySignDetail.length() - dayOfMonth) == '1') {
                log.warn("【dailySignIn】【重复签到】【用户：{}，本月第 {} 天签到】", userId, dayOfMonth);
                throw new BizException(500, I18nUtil.getMessage("biz.SignIn.duplicate.error"));
            }
            // 每日签到后，数值需要进行 或 运算
            int newDailySignDetail = dailySignEntity.getDetail() | 1 << (dayOfMonth - 1);
            newEntity.setDetail(newDailySignDetail);
            byte continuousNum = this.calcContinuousNum(newDailySignDetail);
            newEntity.setContinuousNum(continuousNum);
            dailySignInMapper.updateByPrimaryKey(newEntity);
        }
    }


    private void checkDate(int month, int dayOfMonth) {
        String dateStr = String.valueOf(month * 100 + dayOfMonth);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);// 解析成功，就说明是正常合规的日期
            if (localDate.isAfter(LocalDate.now())) {
                throw new BizException(500, I18nUtil.getMessage("biz.DailySignVo.dayOfMonth.error.2", dateStr));
            }
        } catch (DateTimeParseException e) { // 解析失败，日期不合法
            throw new BizException(500, I18nUtil.getMessage("biz.DailySignVo.dayOfMonth.error.1", dateStr));
        }
    }


    /**
     * 功能描述：计算 当月最大连续签到次数
     *
     * @param dailySignDetail 当月签到详情
     * @return 当月最大连续签到次数
     */
    private byte calcContinuousNum(int dailySignDetail) {
        String continuousNum = Integer.toBinaryString(dailySignDetail);
        // 当月最大连续签到次数 0111011，这个算法没问题
        byte maxContinuousNum = 0;
        byte temp = 0;
        for (int i = 0; i < continuousNum.length(); i++) {
            char ch = continuousNum.charAt(i);
            if (ch == '1') { // 1签到、0未签到
                ++temp;
            } else {
                temp = 0;
            }
            if (temp > maxContinuousNum) {
                maxContinuousNum = temp;
            }
        }
        return maxContinuousNum;
    }

}
