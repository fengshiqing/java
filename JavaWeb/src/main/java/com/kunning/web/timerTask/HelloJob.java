package com.kunning.web.timerTask;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * <p>
 * This is just a simple job that says "Hello" to the world.
 * </p>
 * 
 * @author Bill Kratzer
 */
public class HelloJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    /**
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with
     * the <code>Job</code>.
     */
    public void execute(JobExecutionContext context) {
        // Say Hello to the World and display the date/time
        LOGGER.info("【Hello World!】 - " + new Date());
    }

}
