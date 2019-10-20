package com.kunning.SpringBoot.timerTask;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    // 通过创建匿名类方式实现

    public static void main(String[] args) {
        // 首先初始化一个TimerTask的匿名子类
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("开始执行"); //退出程序返回最上层，0表示正常退出程序，非0表示非正常退出程序
                System.exit(0);
            }
        };
        //通过Timer调用
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }
}
