/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.aop;

import com.kunning.springboot.service.RedisService;
import com.kunning.springboot.utils.SpelParserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 功能描述：redis缓存切面
 *
 * @author fengshiqing
 * @since 2020-08-08
 */
@Aspect
@Component
public class RedisCacheAspect {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisCacheAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 功能描述：定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.kunning.springboot.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 功能描述：定义切面: 所有加RedisLock注解的方法
     */
    @Pointcut(value = "@annotation(com.kunning.springboot.aop.RedisCache)")
    public void servicePointcut1() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     *
     * @param joinPoint joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        LOGGER.info("URL : " + request.getRequestURL().toString());
        LOGGER.info("HTTP_METHOD : " + request.getMethod());
        LOGGER.info("IP : " + request.getRemoteAddr());
        LOGGER.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        LOGGER.info("RESPONSE : " + ret);
    }

    /**
     * @param pjp        连接点，对增强方法的封装
     * @param redisCache 注解
     *
     * @return Object
     */
    // @Around("@annotation(com.kunning.springboot.aop.RedisCache)") 可以简写成如下形式
    @Around("@annotation(redisCache)")
    public Object doAround(ProceedingJoinPoint pjp, RedisCache redisCache) throws Throwable {
        String key = redisCache.key(); // 获取key

        Parameter[] parameters = ((MethodSignature) pjp.getSignature()).getMethod().getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterNames[i] = parameters[i].getName();
        }

        System.out.println("【类名】：" + pjp.getSourceLocation().getWithinType().getName());
        System.out.println("【方法名】：" + pjp.getSignature().getName());

        String redisKey = SpelParserUtil.getKey(key, parameterNames, pjp.getArgs());
        if (redisKey != null) {
            String redisValue = (String) redisTemplate.opsForValue().get(redisKey);
            if (redisValue != null) {
                System.out.println("【redisValue:{}】" + redisValue);
                return redisValue;
            } else {
                Object object = pjp.proceed(); // 执行业务代码，并获取返回值
                if (object != null) {
                    redisTemplate.opsForValue().set(redisKey, object, 3600L); // 从数据库中查到数据就写入缓存
                } else {
                    // 数据库中也查不到数据，就就写入缓"null"字符串，防止DDOS攻击。
                    redisTemplate.opsForValue().set(redisKey, "null", 30L);
                }
                return object;
            }
        } else {
            LOGGER.error("【获取redisKey时发生错误】【redisKey is null】");
            return null;
        }
    }

}
