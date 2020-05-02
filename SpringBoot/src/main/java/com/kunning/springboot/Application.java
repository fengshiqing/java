package com.kunning.springboot;

import com.kunning.springbootstarter.CustomStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用程序入口
 *
 * @author 冯仕清
 */
@SpringBootApplication // 标注主程序类，表明这是一个Spring Boot应用
@EnableScheduling
public class Application implements CommandLineRunner {

//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }

    @Autowired
    private CustomStarter customStarter;

    // 自定义运行 其他代码
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        customStarter.welcome();
    }
}
