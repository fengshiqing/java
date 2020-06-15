package com.kunning.springboot.service;

import java.util.List;

import com.kunning.springboot.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.User;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Service
public class UserService implements UserDetailsService{

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDao userMapper;

	public List<User> queryAllUser() {
		LOGGER.info("【queryAllUser】【开始执行】");

		List<User> userList = userMapper.queryAllUser();
		LOGGER.info("【参数：】【userList:{}】", userList);

		return userList;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("用户的用户名: {}", username);
        // TODO 根据用户名，查找到对应的密码，与权限

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(username, "123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;
        return null;
    }

}
