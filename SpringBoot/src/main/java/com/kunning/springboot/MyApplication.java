package com.kunning.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述：应用程序入口
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
// 标注主程序类，表明这是一个Spring Boot应用
@SpringBootApplication
@EnableScheduling // 开启定时任务
@EnableFeignClients
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
