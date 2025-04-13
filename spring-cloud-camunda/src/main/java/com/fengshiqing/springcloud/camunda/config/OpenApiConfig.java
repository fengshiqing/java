/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.camunda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述：配置 springdoc
 * 访问地址：<a href="http://localhost:8080/cloud-application/swagger-ui/index.html">...</a>，项目中我配置了 URL 前缀
 *
 * @author 冯仕清
 * @since 2025-04-06
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Spring Boot 3.0 API")
                        .version("1.0")
                        .description("Spring Boot 3.0 应用API文档")
                        .contact(new Contact()
                                .name("开发团队")
                                .email("938481169@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}