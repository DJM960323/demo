package com.example.demo.exam.service;

import com.example.demo.exam.api.param.UserParam;
import com.example.demo.exam.dao.UserDao;
import com.example.demo.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 邓建明 on 2019-4-25.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     *
     * @param userParam
     */
    public void addUser(UserParam userParam){
        userDao.addUser(userParam);
    }
    public void deleteUser(Integer id){
        userDao.deleteUser(id);
    }

    public List<User> findUserById(Integer id){
        return userDao.findUserById(id);
    }
    public List<User> findAllUser(){
        return userDao.findAllUser();
    }

    public void updateUser(UserParam userParam,Integer id){
        userDao.updateUser(userParam,id);
    }
}
