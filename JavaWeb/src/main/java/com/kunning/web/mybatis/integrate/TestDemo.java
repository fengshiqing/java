package com.kunning.web.mybatis.integrate;

import com.kunning.web.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 功能描述：学习 SPring 整合 Mybatis
 * 看这个：https://www.ixigua.com/i6802561215391334915/
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
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(TestDemo.class); // 不使用XML 配置文件，直接使用注解配置Spring
        LOGGER.info("【User类型】[{}]", appCtx.getBean(User.class));
        LOGGER.info("【User类型】[{}]", new User());

        LOGGER.info("【参数2】[{}]", appCtx.getId());
        LOGGER.info("【参数5】[{}]", appCtx.getStartupDate());


        LOGGER.info("【参数】[{}]", UserDao.class);
//        LOGGER.info("【参数】[{}]", appCtx.getBean(UserDao.class));
        LOGGER.info("【main】【结束执行】");
    }
}
