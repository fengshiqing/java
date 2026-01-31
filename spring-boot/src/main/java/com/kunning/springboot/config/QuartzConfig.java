package com.kunning.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kunning.springboot.task.QuartzTask;

/**
 * 功能描述：配置 Quartz。
 *
 * @author 冯仕清
 * @since 2019/12/10
 */
@Slf4j
@Configuration
public class QuartzConfig {

    /**
     * 构造函数
     */
    public QuartzConfig() {
        log.info("【初始化 Quartz 配置】");
    }

    @Bean
    public JobDetail quartzTestDetail() {
        return JobBuilder.newJob(QuartzTask.class)
                .withIdentity("quartzTestDetail")
                .usingJobData("userName", "susan")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(quartzTestDetail())
                .withIdentity("quartzTestDetail")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
