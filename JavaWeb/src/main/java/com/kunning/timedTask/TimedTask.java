package com.kunning.timedTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimedTask {

    @Scheduled(cron="0/3 * * * * ?")
    public void schedule_1() {
        System.out.println("【定时任务】");
    }

}
