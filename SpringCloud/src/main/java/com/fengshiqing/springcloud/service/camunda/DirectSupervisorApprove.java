/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 功能描述：请假流程，直接主管审批
 *
 * @author fengshiqing 冯仕清
 * @since 2024-08-10
 */
@Slf4j
public class DirectSupervisorApprove implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("【请假流程，直接主管审批】【execution：{}】", execution);

    }
}
