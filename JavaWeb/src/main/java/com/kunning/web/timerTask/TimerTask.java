package com.kunning.web.timerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {

    @Scheduled(cron="0 0/1 * * * ?")
    public void schedule_1() {
//        System.out.println("【定时任务：使用Spring框架的@Scheduled配置定时任务】" + System.currentTimeMillis());
    }

}
