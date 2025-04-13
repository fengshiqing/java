/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.mapper.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 功能描述：日常签到表 t_user_daily_sign_in 表 的实体类。
 *
 * @author fengshiqing
 * @since 2023-09-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DailySignEntity {

    /**
     * 用户ID，和month组成联合主键
     */
    private String userId;

    /**
     * 签到月份，示例：202309，202310
     */
    private int month;

    /**
     * 当月的签到记录
     */
    private int detail;

    /**
     * 当月最大连续签到次数
     */
    private int continuousNum;

    /**
     * 当月签到总次数
     */
    private int totalNum;


    public DailySignEntity(String userId, int month) {
        this.userId = userId;
        this.month = month;
    }
}