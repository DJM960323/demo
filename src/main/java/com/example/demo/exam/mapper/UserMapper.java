package com.example.demo.exam.mapper;

import com.example.demo.exam.Mapper;
import com.example.demo.exam.api.param.UserParam;
import com.example.demo.exam.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 邓建明 on 2019-4-25.
 */
public interface UserMapper extends Mapper<User>{

    /**
     * 添加用户
     * @param userParam
     */
    void addUser(@Param("userParam") UserParam userParam);

    void deleteUser(@Param("id") Integer id);

    List<User> findUserById(@Param("id") Integer id);

    List<User> findAllUser();

    void updateUser(@Param("userParam") UserParam userParam, @Param("id") Integer id);
}
