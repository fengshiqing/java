package com.kunning.javaSE.timerTask;

import com.kunning.javaSE.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 基于实现接口的定时任务。
 * 可以动态改变定时任务的执行周期。
 */
@Component
@EnableScheduling // 开启定时任务
public class TimedTask_2 implements SchedulingConfigurer {

    @Autowired
    CronService cronService;// 可以从数据库获取cron表达式。

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
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    String cron = cronService.queryCron();// 2.1可以从数据可中获取执行周期，这样就可以动态的改变定时任务的执行周期
                    if (StringUtils.isEmpty(cron)) {// 2.2 合法性校验
                        // Omitted Code ..
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);//2.3 返回执行周期(Date)
                }
        );
    }
}
