package com.fengshiqing.springai.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fengshiqing.springai.constant.PageResult;
import com.fengshiqing.springai.constant.MessageConstant;
import com.fengshiqing.springai.constant.PasswordConstant;
import com.fengshiqing.springai.constant.StatusConstant;
import com.fengshiqing.springai.config.BaseContext;
import com.fengshiqing.springai.dao.entity.User;
import com.fengshiqing.springai.model.exception.AccountLockedException;
import com.fengshiqing.springai.model.exception.AccountNotFoundException;
import com.fengshiqing.springai.model.exception.PasswordErrorException;
import com.fengshiqing.springai.model.dto.UserDTO;
import com.fengshiqing.springai.model.dto.UserPageQueryDTO;
import com.fengshiqing.springai.dao.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.util.List;


/**
* @author 冯仕清
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-02-14 21:05:04
*/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User login(String userName, String password)  {

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(userName);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        //对前端传过来的明文密码进行md5加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (user.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return user;
    }


    public void saveUser(UserDTO userDTO) {
        User user = new User();

        //对象属性拷贝
        BeanUtils.copyProperties(userDTO, user);

        //设置账号的状态，默认正常状态 1表示正常 0表示锁定
        user.setStatus(StatusConstant.ENABLE);

        //设置密码，默认密码123456
        user.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        user.setCreateTime(LocalDate.now());
        user.setUpdateTime(LocalDate.now());

        //设置当前记录创建人id和修改人id
        user.setCreateUser(BaseContext.getCurrentId());
        user.setUpdateUser(BaseContext.getCurrentId());

        userMapper.insert(user);
    }


    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());

        Page<User> page = userMapper.pageQuery(userPageQueryDTO);

        long total = page.size();
        List<User> records = page.getResult();

        return new PageResult(total, records);
    }


    public void startOrStop(Integer status, Integer id) {
        User user = User.builder()
                .status(status)
                .id(id)
                .build();

        userMapper.updateUser(user);
    }


    public void updateUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        user.setUpdateTime(LocalDate.now());
        user.setUpdateUser(BaseContext.getCurrentId());

        userMapper.updateUser(user);
    }


    public void register(User user) {
        User userResult = new User();
        BeanUtils.copyProperties(user, userResult);
        //设置账号的状态，默认正常状态 1表示正常 0表示锁定
        userResult.setStatus(StatusConstant.ENABLE);
        //设置密码，默认密码123456
        userResult.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //设置当前记录的创建时间和修改时间
        userResult.setCreateTime(LocalDate.now());
        userResult.setUpdateTime(LocalDate.now());

        //设置当前记录创建人id和修改人id
        userResult.setCreateUser(BaseContext.getCurrentId());
        userResult.setUpdateUser(BaseContext.getCurrentId());
        userMapper.insert(userResult);
    }


    public boolean getByUsername(String userName) {
        if (userMapper.getByUsername(userName) != null) {
            return true;
        }
        return false;
    }


    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }


    public int update(User user) {
        userMapper.updateUser(user);
        return 1;
    }
}




