package com.example.demo.code.mapper;

import com.example.demo.code.Mapper;
import com.example.demo.code.api.param.UserParam;
import com.example.demo.code.entity.User;
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
    void registerUser(@Param("userParam") UserParam userParam);

    /**
     * 根据用户id注销用户信息
     * @param id
     */
    void deleteUser(@Param("id") Integer id);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    List<User> findUserById(@Param("id") Integer id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据用户id修改用户信息
     * @param userParam
     * @param id
     */
    void updateUser(@Param("userParam") UserParam userParam, @Param("id") Integer id);

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    User login(@Param("name")String name,@Param("password") String password);

    /**
     * 验证手机号的唯一性
     * @param phone
     * @return
     */
    Integer validateUniquePhone(@Param("phone") String phone);

    /**
     * 验证用户名的唯一性
     * @param name
     * @return
     */
    Integer validateUniqueName(@Param("name") String name);
}
