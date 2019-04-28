package com.example.demo.code.service;

import com.example.demo.code.api.dto.UserDto;
import com.example.demo.code.api.param.LoginParam;
import com.example.demo.code.api.param.UserParam;
import com.example.demo.code.dao.UserDao;
import com.example.demo.code.entity.User;
import com.example.demo.code.mapper.UserMapper;
import com.example.demo.excption.ResponseCode;
import com.example.demo.excption.WebException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 邓建明 on 2019-4-25.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    /**
     *用户注册，并判断用户名跟手机号是否已被使用
     * @param userParam
     */
    public void registerUser(UserParam userParam){
        if(userMapper.validateUniqueName(userParam.getName()) > 0){
            throw new WebException(ResponseCode.用户名已存在.getCode(),ResponseCode.用户名已存在.getMsg());
        }
        if(userMapper.validateUniquePhone(userParam.getPhone()) > 0){
            throw new WebException(ResponseCode.手机号已被注册.getCode(),ResponseCode.手机号已被注册.getMsg());
        }
        userDao.registerUser(userParam);
    }

    /**
     * 注销用户
     * @param id
     */
    public void deleteUser(Integer id){
        userDao.deleteUser(id);
    }

    /**
     * 通过用户id查询用户信息
     * @param id
     * @return
     */
    public List<User> findUserById(Integer id){
        return userDao.findUserById(id);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    /**
     * 根据用户id修改用户信息
     * @param userParam
     * @param id
     */
    public void updateUser(UserParam userParam,Integer id){
        userDao.updateUser(userParam,id);
    }

    /**
     *简单的用户登录
     * @param loginParam
     * @return
     */
    @Cacheable
    public UserDto login(LoginParam loginParam){
        UserDto userDto = new UserDto();
        User user = userMapper.login(loginParam.getName(),loginParam.getPassword());
        if(user != null){
            UsernamePasswordToken token = new UsernamePasswordToken(user.getId().toString(),loginParam.getPassword());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setPhone(user.getPhone());
            System.out.println("登录成功");
        }else{
            throw new WebException(ResponseCode.用户名或密码错误.getCode(),ResponseCode.用户名或密码错误.getMsg());
        }
        return userDto;
    }

}
