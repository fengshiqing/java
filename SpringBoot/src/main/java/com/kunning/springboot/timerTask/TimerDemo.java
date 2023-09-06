/*
 * Copyright (c) 2021. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.timerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 功能描述：JDK自带的定时任务。
 */
public class TimerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerDemo.class);
    // 通过创建匿名类方式实现

    public static void main(String[] args) {
        // 首先初始化一个TimerTask的匿名子类
        TimerTask task = new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                try {
                    // 在单独的进程中执行指定的字符串命令，可以直接调用CMD执行命令，还可以调用其他.exe程序执行命令
                    Process process = Runtime.getRuntime().exec(""); // 可以看这个：https://blog.csdn.net/qq_34997906/article/details/91041256
                    LOGGER.info("【执行本机软件】" + process.waitFor() + "---" + count++);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 以下是几种常用调度task的方法：
        // timer.schedule(task, time); // time为Date类型：在指定时间执行一次。
        // timer.schedule(task, firstTime, period); // firstTime为Date类型,period为long
        // 从firstTime时刻开始，每隔period毫秒执行一次。
        // timer.schedule(task, delay); // delay 为long类型：从现在起过delay毫秒执行一次
        // timer.schedule(task, delay, period); // delay为long,period为long：从现在起过delay毫秒以后，每隔period 毫秒执行一次。
        new Timer().schedule(task, 1000L, 5000L); // 通过Timer调用
    }
}
