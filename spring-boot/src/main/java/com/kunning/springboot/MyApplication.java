package com.kunning.springboot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述：应用程序入口
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.kunning.springboot.dao")
@EnableScheduling // 开启定时任务
public class MyApplication {

    /**
     * 全局 ApplicationContext 对象
     */
    public static ConfigurableApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(MyApplication.class, args);

        log.info("                ┏━━┓╻  ╻┏━━╸┏━━╸┏━━╸┏━━┓┏━━┓");
        log.info("                ┗━━┓┃  ┃┃   ┃   ┣━━╸┗━━┓┗━━┓");
        log.info("                ┗━━┛┗━━┛┗━━╸┗━━╸┗━━╸┗━━┛┗━━┛");
    }

}
