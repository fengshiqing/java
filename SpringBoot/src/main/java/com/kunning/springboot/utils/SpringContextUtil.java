package com.kunning.springboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 功能描述：从Spring上下文取出Bean工具类
 *
 * @author 冯仕清
 * @since 2020-06-21
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring上下文
     */
    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 功能描述：通过name获取 Bean.
     *
     * @param name bean名称
     *
     * @return bean实例
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 功能描述：通过class获取Bean.
     *
     * @param clazz bean类型
     * @param <T>   bean类型
     *
     * @return bean实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 功能描述：通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean名称
     * @param clazz bean类型
     * @param <T>   bean类型
     *
     * @return bean实例
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
