/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.aop;

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
 *
 * <a https://baijiahao.baidu.com/s?id=1666358028563141862&wfr=spider&for=pc />
 * 最典型的横切关注点有日志记录、性能统计、安全控制、事务处理、异常处理、缓存等。
 * AOP已经有了自己的一些术语。描述切面的常用术语有通知（advice）、切点（point)、和连接点。
 *
 * 5种类型的通知。
 * 前置通知（Before):在目标方法被调用之前调用通知功能。
 * 后置通知（After):在目标方法完成之后调用通知，此时不关心方法的输出是什么。
 * 返回通知（After-returning)：在目标方法成功执行之后调用通知。
 * 异常通知（After-throwing)：在目标方法抛出异常后调用。
 * 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。
 */
@Aspect
@Component
public class RedisCacheAspect {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    /**
     * Spring提供的redis模板
     */
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisCacheAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // =================================================================================================================

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

        Parameter[] parameters = ((MethodSignature) pjp.getSignature()).getMethod().getParameters();
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            parameterNames[i] = parameters[i].getName();
        }

        System.out.println("【类名】：" + pjp.getSourceLocation().getWithinType().getName());
        System.out.println("【方法名】：" + pjp.getSignature().getName());

        String redisKey = SpelParserUtil.getKey(redisCache.key(), parameterNames, pjp.getArgs());
        if (redisKey != null) {
            LOGGER.info("【redisKey:{}】", redisKey);
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
