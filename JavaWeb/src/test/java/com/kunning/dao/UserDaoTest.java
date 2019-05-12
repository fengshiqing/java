package com.kunning.dao;

import com.kunning.pojo.User;
import com.kunning.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mvc.xml"})
public class UserDaoTest {

    /**
     * <日志>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void queryUser() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        List<User> userList = userDao.queryUser(user);
        LOGGER.info("【查询的数据：】【userList:{}】", userList);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        int num = userDao.saveUser(user);
    }
}