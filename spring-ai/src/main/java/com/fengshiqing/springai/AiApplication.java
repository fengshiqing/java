/*
 * Copyright (c) fengshiqing 冯仕清 2025. All Rights Reserved.
 */

package com.fengshiqing.springai;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@MapperScan("com.fengshiqing.springai.dao")
public class AiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class, args);

        log.info("      ┏━━┓╻  ╻┏━━╸┏━━╸┏━━╸┏━━┓┏━━┓");
        log.info("      ┗━━┓┃  ┃┃   ┃   ┣━━╸┗━━┓┗━━┓");
        log.info("      ┗━━┛┗━━┛┗━━╸┗━━╸┗━━╸┗━━┛┗━━┛");
    }

}
