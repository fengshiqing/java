/*
 * Copyright (c) 2022. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.kunning.springcloud.aspect;


import com.fengshiqing.common.bean.Resp;
import com.kunning.springcloud.utils.I18nUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 功能描述：记录"请求-响应"日志。
 *
 * @author fengshiqing
 * @since 2022-09-25
 * <p>
 * <a href="https://baijiahao.baidu.com/s?id=1666358028563141862&wfr=spider&for=pc" />
 * 最典型的横切关注点有日志记录、性能统计、安全控制、事务处理、异常处理、缓存等。
 * AOP已经有了自己的一些术语。描述切面的常用术语有通知（advice）、切点（point)、和连接点。
 * <p>
 * 5种类型的通知。
 * 前置通知（Before):在目标方法被调用之前调用通知功能。
 * 后置通知（After):在目标方法完成之后调用通知，不管方法是否抛出异常，都会走这个方法。
 * 返回通知（After-returning)：在目标方法成功执行之后调用通知。如果目标方法方法是void返回类型则返回NULL
 * 异常通知（After-throwing)：在目标方法抛出异常后调用。
 * 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行自定义的行为。
 */
@Slf4j
@Aspect
@Component
public class AccessLogAspect {

    /**
     * 定义切点，切入点为控制层的所有函数
     */
    @Pointcut("execution(* com.kunning.springcloud.controller..*.*(..))")
    public void accessLog() {
    }

    /**
     * 前置通知：在连接点之前执行。
     *
     * @param joinPoint joinPoint
     */
    @Before(value = "accessLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("【前置通知】【类名 # 方法名:{}】", joinPoint.getSignature().getDeclaringTypeName() + " # " + joinPoint.getSignature().getName());
        log.info("【前置通知】【请求参数:{}】", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置通知：在连接点之后执行。
     *
     * @param joinPoint 方法返回值
     */
    @After(value = "accessLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        log.info("【后置通知】【类名 # 方法名:{}】", joinPoint.getSignature().getDeclaringTypeName() + " # " + joinPoint.getSignature().getName());
        log.info("【后置通知】【请求参数:{}】", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 返回通知：在目标方法成功执行之后调用通知。
     *
     * @param result 方法返回值
     */
    @AfterReturning(returning = "result", pointcut = "accessLog()")
    public void doAfterReturning(Object result) {
        // 处理完请求，返回内容
        log.info("【返回通知】【result:{}】", result);
    }

    /**
     * 环绕通知：在连接点前后都执行。
     *
     * @param pjp joinPoint
     *
     * @return 响应参数
     */
    @Around(value = "accessLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        try {
            long start = System.currentTimeMillis();
            Object result = pjp.proceed(); // 进入业务逻辑
            // 异步记录日志/发送MQ消息记录日志等
            log.info("【环绕通知】【类名#方法名:{}】", pjp.getSignature().getDeclaringTypeName() + "#" + pjp.getSignature().getName());
            log.info("【环绕通知】【请求参数:{}】", Arrays.toString(pjp.getArgs()));
            log.info("【环绕通知】【响应参数:{}】", result);
            log.info("【环绕通知】【响应时间:{}ms】", System.currentTimeMillis() - start);
            return result;
        } catch (Throwable e) {
            log.error("【AccessLogAspect】【环绕通知】【发生异常】", e);
            return new Resp(500, I18nUtil.getMessage("system.error"));
        }
    }

}
