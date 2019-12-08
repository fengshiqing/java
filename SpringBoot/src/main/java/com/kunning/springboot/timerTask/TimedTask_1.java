package com.kunning.springboot.timerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 功能描述：基于注解配置的定时任务。
 * 使用注解很方便，但缺点是当调整了执行周期时，需要重启应用才能生效。为了达到实时生效的效果，可以使用接口来完成定时任务。
 */
@Component
@EnableScheduling // 开启定时任务
@EnableAsync // 允许多线程的运行定时任务
public class TimedTask_1 {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimedTask_1.class);

    @Scheduled(cron="0 0/5 * * * ?")
    public void schedule_1() {
        LOGGER.info("【定时任务1】");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void schedule_2() {
        LOGGER.info("【定时任务2】");
    }

    // 多线程定时任务
    @Async
    @Scheduled(cron="0 0/5 * * * ?")
    public void schedule_3() {
        LOGGER.info("【定时任务3】");
    }

    @Async
    @Scheduled(cron="0 0/5 * * * ?")
    public void schedule_4() {
        LOGGER.info("【定时任务4】");
    }

}
