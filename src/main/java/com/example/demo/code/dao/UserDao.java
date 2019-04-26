package com.example.demo.code.dao;

import com.example.demo.code.api.param.UserParam;
import com.example.demo.code.entity.User;
import com.example.demo.code.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 邓建明 on 2019-4-25.
 */
@Component
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    /**
     *注册新用户
     * @param userParam
     */
    public void registerUser(UserParam userParam){
        userMapper.registerUser(userParam);
    }

    /**
     * 注销指定id的用户
     * @param id
     */
    public void deleteUser(Integer id){
        userMapper.deleteUser(id);
    }

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public List<User> findUserById(Integer id){
        return userMapper.findUserById(id);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    /**
     * 根据用户id修改用户信息
     * @param userParam
     * @param id
     */
    public void updateUser(UserParam userParam,Integer id){
        userMapper.updateUser(userParam,id);
    }
}
