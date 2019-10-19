package com.kunning.javaWeb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <学习日志框架。><br>
 * <配置文件：log4j.properties>
 *
 * @author 冯仕清
 */
public class LoggerDemo {

    /**
     * <日志>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerDemo.class);

    public static void main(String[] args) {
        String name = Logger.ROOT_LOGGER_NAME;
        System.out.println(name); // false 说明root无法通过name获取

        System.out.println(LOGGER.getName());
        System.out.println(LOGGER.isDebugEnabled());
        System.out.println(LOGGER.isInfoEnabled());

        LOGGER.info("【LoggerDemo】【main】【开始执行】【参数】", " sdfsdfasfd");
        // slf4j 支持占位符，相比log4j 节省内存，性能高效。
        LOGGER.info("【参数：】【{}，{}，{}，{}，{}，{}，{}，{}】", "fengshiqing", "sdfsadf", "sdf", "fef", "冯仕清", "sdfsadf", "sdf", "fef");
    }

}
