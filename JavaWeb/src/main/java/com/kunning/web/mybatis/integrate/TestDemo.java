/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.web.mybatis.integrate;

import com.fengshiqing.common.model.User;
import com.kunning.web.mybatis.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 功能描述：学习 SPring 整合 Mybatis
 * 看这个：<a href="https://www.ixigua.com/i6802561215391334915/">...</a>
 *
 * @author 冯仕清
 * @since 2020/04/19
 */
@Component
@ComponentScan("com.kunning.web.mybatis")
public class TestDemo {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDemo.class);

    /**
     * 主函数
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        LOGGER.info("【main】【开始执行】");
        ApplicationContext context = new AnnotationConfigApplicationContext(TestDemo.class); // 不使用XML 配置文件，直接使用注解配置Spring
        LOGGER.info("【User类型】[{}]", context.getBean(User.class));
        LOGGER.info("【User类型】[{}]", new User());

        System.out.println();

        LOGGER.info("【UserDao】[{}]", UserDao.class);
        LOGGER.info("【FactoryBean】[{}]", context.getBean(LubanFactoryBean.class)); // 打印的是 调用了toString方法的结果
        LOGGER.info("【FactoryBean】[{}]", context.getBean("lubanFactoryBean"));
        UserDao userDao = context.getBean("lubanFactoryBean", UserDao.class);
        userDao.getOne(123L);
        LOGGER.info("【main】【结束执行】");
    }
}
