package com.kunning.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.UserDto;
import org.springframework.validation.annotation.Validated;

/**
 * 功能描述：用户服务。
 *
 * 使用Spring优雅实现观察者模式。
 *
 * 观察者模式定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新，其主要解决一个对象状态改变给其他关联对象通知的问题，保证易用和低耦合。
 * 一个典型的应用场景是：当用户注册以后，需要给用户发送邮件，发送优惠券等操作
 *
 * SpringBoot 使用 validation 数据校验，参考：https://mp.weixin.qq.com/s/q-AMTmV9YobrrfkiF8sCvg
 *
 * @author 冯仕清
 * @since 2019-10-01
 */
@Service
@Validated // 单个参数(非对象)的校验，需要在类上添加此注解，否则不会校验
public class UserService implements ApplicationEventPublisherAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 构造函数
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 功能描述：ApplicationEventPublisher
     * 
     * @param applicationEventPublisher 通过实现它，来发布变更事件。
     */
    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 功能描述：新用户注册
     *
     * @param userDto 用户信息
     */
    public void register(UserDto userDto) {
        // ... 执行注册逻辑
        LOGGER.info("【register】【执行用户({}) 的注册逻辑】", userDto.getUsername());
        // 用户名不能重复
        // 密码强度低
        userDao.insert(userDto); // 新用户注册
        // <2> ... 发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, userDto.getUsername()));
    }

    /**
     * 功能描述：查询全部用户
     *
     * @return 用户列表
     */
    public List<UserDto> queryAllUser() {
        LOGGER.info("【queryAllUser】【start】");

        List<UserDto> userDtoList = userDao.queryAllUser();
        LOGGER.info("【参数：】【userList:{}】", userDtoList);

        return userDtoList;
    }

    public UserDto queryUserById(int userId) {
        LOGGER.info("【queryAllUser】【start】");

        UserDto userDto = userDao.queryUserById(userId);
        LOGGER.info("【参数：】【userDto:{}】", userDto);

        return userDto;
    }
}
