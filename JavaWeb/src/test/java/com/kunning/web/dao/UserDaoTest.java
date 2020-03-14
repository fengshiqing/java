package com.kunning.web.dao;

import com.kunning.web.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-mvc.xml"})
public class UserDaoTest {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        int num = userDao.addUser(user);
        LOGGER.info("【addUser】【执行成功】【响应参数：】【num:{}】", num);
    }

    @Test
    public void queryUser() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        List<User> userList = userDao.queryUser(user);
        LOGGER.info("【查询的数据：】【userList:{}】", userList);
    }
}