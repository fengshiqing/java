/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 功能描述：用户信息
 *
 * @author 冯仕清
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
    private String userId;


    /**
     * 流程的流水号ID
     */
    private String flowId;


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

    /**
     * 有效/是否被删除标记
     */
    private Date delFlag;

    public UserFlowEntity(String userId, String flowId, String flowType, String currentApprover, String remark) {
        this.userId = userId;
        this.flowId = flowId;
        this.flowType = flowType;
        this.currentApprover = currentApprover;
        this.remark = remark;
        this.createUser = userId;
        this.updateUser = userId;
    }

}