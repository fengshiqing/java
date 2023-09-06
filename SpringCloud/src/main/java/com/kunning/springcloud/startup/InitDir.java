/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springcloud.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 功能描述：项目启动时，初始化项目中需要用到的目录结构，避免每次使用时都要校验目录是否存在。
 *
 * @author fengshiqing
 * @since 2022-01-08
 */
@Service
@Order(0)
public class InitDir implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitDir.class);

    @Override
    public void run(String... args) {
        LOGGER.info("【执行顺序 00】");
        System.out.println("【执行顺序 00】");

        String usrHome = System.getProperty("user.home"); // 执行当前程序的用户的家目录，windows上是"C:\Users\fengshiqing\"
        File dirPath = new File(usrHome + "/temp");
        if (!dirPath.exists() && !dirPath.mkdir()) { // 临时目录不存在且创建失败，就结束掉java进程，手动去创建下。
            LOGGER.error("【启动项目时，创建必须的文件夹 失败】【请手动去创建下/temp目录】");
            System.exit(-1);
        }

        // 方式一：
        dirPath.setReadable(true);//设置可读权限
        dirPath.setWritable(true);//设置可写权限
        dirPath.setExecutable(false);//设置可执行权限
        // 方式二：
        // Runtime.getRuntime().exec("chmod 666 " + dirPath);
    }
}
