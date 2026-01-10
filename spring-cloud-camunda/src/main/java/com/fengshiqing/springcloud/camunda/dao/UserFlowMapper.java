/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.dao;

import com.fengshiqing.springcloud.camunda.dao.entity.UserFlowEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能描述：用户流程Dao
 *
 * @author fengshiqing 冯仕清
 * @since 2024-08-11
 */
@Repository
public interface UserFlowMapper {

    int insert(UserFlowEntity record);


    int update(@Param("userId") String userId, @Param("flowId") String flowId, @Param("currentApprover") String currentApprover);


    List<UserFlowEntity> selectByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);


    int delete(Long id);

}