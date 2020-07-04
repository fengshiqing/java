package com.kunning.springboot.service;

import java.util.List;
import java.util.Objects;

import com.kunning.springboot.dao.UserDao;
import com.kunning.springboot.pojo.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserDao userDao;

    /**
     * 功能描述：登录
     *
     * @param userDto 用户信息
     */
    public int insert(UserDto userDto) {
        Objects.requireNonNull(userDto, "【user不能为null】");
        return userDao.insert(userDto);
    }

    public List<UserDto> queryAllUser() {
        LOGGER.info("【queryAllUser】【start】");

        List<UserDto> userDtoList = userDao.queryAllUser();
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

        Objects.requireNonNull(username, "用户名为空");

        UserDetails userDetails = null; // userMapper.queryUserByUserName(username);
        // TODO 根据用户名，查找到对应的密码，与权限
        if ("wangwu".equals(username)) {
            userDetails = User.withUsername("wangwu").password("123").authorities("p1").build(); // 模拟一个用户数据
        }

        if (userDetails == null) {
            LOGGER.warn("【loadUserByUsername】【账号信息不存在】【username:{}】", username);
            throw new UsernameNotFoundException("账号信息不存在");
        }

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
//        UserDto userDto = new UserDto(username, "123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return user;

        LOGGER.info("【loadUserByUsername】【end】【userDetails:{}】", userDetails);
        return userDetails;
    }

    /**
     * 功能描述：从SpringSecurity中获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        return username;
    }

}
