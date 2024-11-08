/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.camunda;

import com.fengshiqing.springcloud.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * 功能描述：camunda的 TaskListener 监听器
 *
 * @author fengshiqing 冯仕清
 * @since 2024-08-12
 */
@Slf4j
public class UseTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("【UseTaskListener.notify】【start】【delegateTask：{}】", JsonUtil.toJsonStr(delegateTask));
        log.info("【UseTaskListener.notify】【start】【id：{}，assignee：{}】", delegateTask.getId(), delegateTask.getAssignee());
    }

}
