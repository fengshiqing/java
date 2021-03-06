package com.kunning.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述：应用程序入口
 *
 * @author 冯仕清
 */
@SpringBootApplication // 标注主程序类，表明这是一个Spring Boot应用
@EnableScheduling
// @EnableServiceComb
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
