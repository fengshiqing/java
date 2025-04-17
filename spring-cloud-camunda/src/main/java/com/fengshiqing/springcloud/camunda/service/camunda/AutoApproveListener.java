/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.service.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/**
 * 功能描述：camunda的 ExecutionListener 监听器
 *
 * @author fengshiqing 冯仕清
 * @since 2024-08-12
 */
@Slf4j
public class AutoApproveListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        log.info("【AutoApproveListener.notify】【start】【processInstanceId：{}】", execution.getProcessInstanceId());
        log.info("【AutoApproveListener.notify】【end】");
    }

}
