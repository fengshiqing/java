package com.kunning.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 功能描述：学习 @Configuration、@Bean、@Import 注解
 *
 * @author 冯仕清
 * @since 2101/12/25
 */
// https://www.w3cschool.cn/wkspring/tlbk1icp.html
@Configuration // 取代 application.xml配置文件
public class AppConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    /**
     * 构造函数
     */
    public AppConfig() {
        LOGGER.info("【初始化 AppConfig 配置】");
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Foo foo() {
        LOGGER.info("【创建 Foo 对象】");
        return new Foo();
    }

    static class Foo {
        public void init() {
            LOGGER.info("【init 对象 Foo】");
        }

        public void destroy() {
            LOGGER.info("【destroy 对象 Foo】");
        }
    }

    /**
     * 功能描述：配置 redisTemplate。
     * <a href="https://blog.csdn.net/weixin_47933000/article/details/108269241">...</a>
     *
     * @param redisConnectionFactory 参数
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        LOGGER.info("【初始化 redis 配置】");

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer); // 设置key和value的序列化规则
        redisTemplate.setValueSerializer(stringRedisSerializer); // 设置key和value的序列化规则
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }

}
