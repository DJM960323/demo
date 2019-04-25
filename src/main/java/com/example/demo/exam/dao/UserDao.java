package com.example.demo.exam.dao;

import com.example.demo.exam.api.param.UserParam;
import com.example.demo.exam.entity.User;
import com.example.demo.exam.mapper.UserMapper;
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
     *
     * @param userParam
     */
    public void addUser(UserParam userParam){
        userMapper.addUser(userParam);
    }
    public void deleteUser(Integer id){
        userMapper.deleteUser(id);
    }

    public List<User> findUserById(Integer id){
        return userMapper.findUserById(id);
    }

    public List<User> findAllUser(){
        return userMapper.findAllUser();
    }

    public void updateUser(UserParam userParam,Integer id){
        userMapper.updateUser(userParam,id);
    }
}
