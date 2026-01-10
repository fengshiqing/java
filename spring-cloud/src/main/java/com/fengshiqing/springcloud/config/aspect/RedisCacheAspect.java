/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springcloud.config.aspect;

import com.fengshiqing.springcloud.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/**
 * 功能描述：redis缓存切面
 *
 * @author fengshiqing
 * @since 2020-08-08
 * <p>
 * <a href="https://baijiahao.baidu.com/s?id=1666358028563141862&wfr=spider&for=pc" />
 * 最典型的横切关注点有日志记录、性能统计、安全控制、事务处理、异常处理、缓存等。
 * AOP已经有了自己的一些术语。描述切面的常用术语有通知（advice）、切点（point)、和连接点。
 * <p>
 * 5种类型的通知。
 * 前置通知（Before):在目标方法被调用之前调用通知功能。
 * 后置通知（After):在目标方法完成之后调用通知，此时不关心方法的输出是什么。
 * 返回通知（After-returning)：在目标方法成功执行之后调用通知。
 * 异常通知（After-throwing)：在目标方法抛出异常后调用。
 * 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。
 */
@AllArgsConstructor
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    /**
     * Spring提供的 redisTemplate。
     * 必须用 Resource 注解，SpringBoot自动注入的redisTemplate类型为 RedisTemplate<Object, Object>，如果用 Autowired也必须是同样的类型。
     */
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 功能描述：redis缓存数据注解。
     *
     * @param pjp        连接点，对增强方法的封装
     * @param redisCache 注解
     *
     * @return 业务方法的返回值
     */
    @Around("@annotation(redisCache)")
    public Object doAround(ProceedingJoinPoint pjp, RedisCache redisCache) {

        Parameter[] parameters = ((MethodSignature) pjp.getSignature()).getMethod().getParameters();
        String redisKey = redisCache.key();
        for (int i = 0; i < parameters.length; i++) {
            redisKey = redisKey.replaceAll("\\{" + i + "}", String.valueOf(pjp.getArgs()[i]));
        }

        System.out.println("【类名】：" + pjp.getSourceLocation().getWithinType().getName());
        System.out.println("【方法名】：" + pjp.getSignature().getName());

        log.info("【RedisCacheAspect】【redisKey:{}】", redisKey);
        String redisValue = redisTemplate.opsForValue().get(redisKey);
        if (redisValue != null) {
            Class<?> clazz = ((MethodSignature) pjp.getSignature()).getReturnType();
            return JsonUtil.fromJson(redisValue, clazz);
        } else {
            try {
                Object result = pjp.proceed(); // 执行业务代码，并获取返回值
                if (result != null) {
                    redisTemplate.opsForValue().set(redisKey, JsonUtil.toJsonStr(result), redisCache.expire(), redisCache.unit()); // 从数据库中查到数据就写入缓存
                } else {
                    // 数据库中也查不到数据，就就写入缓"null"字符串，防止DDOS攻击。
                    redisTemplate.opsForValue().set(redisKey, "null", 30L);
                }
                return result;
            } catch (Throwable e) {
                log.error("【RedisCacheAspect】【环绕通知】【发生异常】", e);
                throw new RuntimeException(e);
            }
        }
    }

}
