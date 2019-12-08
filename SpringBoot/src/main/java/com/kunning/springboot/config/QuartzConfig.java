package com.kunning.springboot.config;

import com.kunning.springboot.timerTask.TimedTask_1;
import com.kunning.springboot.timerTask.UploadTask;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzConfig.class);

    @Bean
    public JobDetail uploadTaskDetail() {
        return JobBuilder.newJob(UploadTask.class).withIdentity("uploadTask").storeDurably().build();
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
