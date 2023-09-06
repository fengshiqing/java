package com.kunning.springboot.timerTask;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 功能描述：Quartz 定时任务。
 *
 * @author 冯仕清
 * @since 2019/12/10
 */
public class QuartzTask extends QuartzJobBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        LOGGER.info("【Quartz任务开始】");
        LOGGER.info("【上一次任务时间：{}】", jobExecutionContext.getPreviousFireTime());
        LOGGER.info("【当前任务时间：{}】", jobExecutionContext.getFireTime());
        LOGGER.info("【下一次任务时间：{}】", jobExecutionContext.getNextFireTime());
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("【Quartz任务结束】");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
