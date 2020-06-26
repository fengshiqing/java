package com.kunning.springboot.service;

import java.util.List;

import com.kunning.springboot.controller.UserController;
import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userMapper;

    public List<UserDto> queryAllUser() {
        LOGGER.info("【queryAllUser】【start】");

        List<UserDto> userDtoList = userMapper.queryAllUser();
        LOGGER.info("【参数：】【userList:{}】", userDtoList);

        return userDtoList;
    }

    /**
     * 功能描述：根据账号查询用户信息。
     *
     * @param username 用户账号
     *
     * @return 用户信息
     *
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("【loadUserByUsername】【start】【username:{}】", username);

        // TODO 根据用户名，查找到对应的密码，与权限
        UserDetails userDetails = User.withUsername("wangwu").password("123").authorities("p1").build(); // 模拟一个用户数据
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
//        UserDto userDto = new UserDto(username, "123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;

        LOGGER.info("【loadUserByUsername】【end】【userDetails:{}】", userDetails);
        return userDetails;
    }

}
