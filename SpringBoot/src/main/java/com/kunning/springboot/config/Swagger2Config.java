package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能描述：Swagger配置
 * 
 * @author 冯仕清
 * @since 2019年10月1日
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Swagger2Config.class);

	/**
	 * 构造函数
	 */
	public Swagger2Config() {
		LOGGER.info("【初始化 Swagger 配置】");
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo()).groupName("groupName123").select()
				.apis(RequestHandlerSelectors.basePackage("com.kunning.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("冯仕清", "http://www.baidu.com", "938481168@qq.com"); // 创建人、个人网站、邮箱
		return new ApiInfoBuilder().title("后台管理系统").description("此处介绍后台管理系统的详细情况").version("0.0.1").contact(contact).build();
	}

}
