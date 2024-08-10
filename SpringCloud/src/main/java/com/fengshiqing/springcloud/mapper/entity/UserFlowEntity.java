/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.mapper.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 功能描述：用户信息
 *
 * @author fengshiqing
 * @since 2022-04-23
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserFlowEntity {

    /**
     * 用户ID
     */
    private long userId;


    /**
     * 流程的流水号ID
     */
    private long flowId;


    /**
     * 流程类型
     */
    private String flowType;


    /**
     * 当前审批人
     */
    private String currentApprover;

    /**
     * 申请原因/备注
     */
    private String remark;


    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

}