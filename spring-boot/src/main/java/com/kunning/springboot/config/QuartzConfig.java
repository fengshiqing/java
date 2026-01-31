package com.kunning.springboot.config;

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
@Configuration
public class QuartzConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);

    /**
     * 构造函数
     */
    public QuartzConfig() {
        LOGGER.info("【初始化 Quartz 配置】");
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
