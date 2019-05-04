package com.kunning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序入口
 * @SpringBootApplication 标注主程序类，表明这是一个Spring Boot应用
 * 
 * @author 冯仕清
 */
@SpringBootApplication
@MapperScan("com.kunning.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
