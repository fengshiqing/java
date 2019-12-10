package com.kunning.springboot.config;

import com.kunning.springboot.timerTask.QuartzTask;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：配置 Quartz。
 *
 * @author 冯仕清
 * @since 2019/12/10
 */
@Configuration
public class QuartzConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);

    /**
     * 构造函数
     */
    public QuartzConfig() {
        LOGGER.info("【初始化 Quartz 配置】");
    }

    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(QuartzTask.class).withIdentity("uploadTask").storeDurably().build();
    }

    @Bean
    public Trigger uploadTaskTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
                .withIdentity("uploadTask")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
