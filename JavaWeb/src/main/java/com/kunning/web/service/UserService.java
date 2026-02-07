package com.kunning.web.service;

import com.fengshiqing.common.model.User;
import com.kunning.web.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述：用户管理service
 */
@Service
public class UserService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

    /**
     * 功能描述：增加用户
     */
    public int addUser(User user) {
        LOGGER.info("【addUser】【开始执行】【请求参数：】【user:{}】", user);
        return userDao.addUser(user);
    }

    /**
     * 查询用户信息
     */
    // propagation 传播行为，默认的REQUIRED.REQUIRE的就行
    // isolation 隔离级别，最常用的是：READ_COMMITTED
    // 默认情况下，Spring会对所有的运行时异常进行回滚，当然也可以自定义设置
    // readOnly 表示只读取数据，不更新数据，如果是只读的，最好设置成 true，这样可以帮助数据库引擎优化事务
    // timeout 限制事务等待的时间，超过时间，就回滚
    @Transactional
    public List<User> queryUser(User user) {
        LOGGER.info("【queryUser】【开始执行】【请求参数：】【user:{}】", user);
        return userDao.queryUser(user);
    }

    public User queryUser(String username) {
        String sql = "SELECT username, password FROM t_user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rowMapper, username);
        LOGGER.info("【查询到的用户数据：】【user:{}】", user);
        return user;
    }

}
