package com.kunning.springboot.timerTask;

import com.kunning.springboot.service.CronService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 基于实现接口的定时任务。
 * 可以动态改变定时任务的执行周期。
 */
@Component
public class TimedTask_2 implements SchedulingConfigurer {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedTask_2.class);

    private final CronService cronService; // 可以从数据库获取cron表达式。

    @Autowired
    public TimedTask_2(CronService cronService) {
        this.cronService = cronService;
    }

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}.
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> LOGGER.info("【执行动态定时任务】"),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    String cron = cronService.queryCron(); // 可以从数据库、配置项中获取执行周期，这样就可以动态的改变定时任务的执行周期
                    LOGGER.info("【Cron表达式为】【cron:{}】", cron);
                    return new CronTrigger(cron).nextExecutionTime(triggerContext); // 返回执行周期(Date)
                }
        );
    }
}
