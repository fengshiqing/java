package com.kunning.springboot.timerTask;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UploadTask extends QuartzJobBean {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        LOGGER.info("【任务开始】");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("【任务结束】");
    }

}
