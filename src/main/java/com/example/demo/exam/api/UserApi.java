package com.example.demo.exam.api;

import com.example.demo.exam.api.param.UserParam;
import com.example.demo.exam.entity.User;
import com.example.demo.exam.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 邓建明 on 2019-4-25.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(value = "用户接口", description = "邓建明")
public class UserApi {

    @Autowired
    private UserService userService;

    /**
     *
     * @param userParam
     */
    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public void addUser(@RequestBody UserParam userParam){
        userService.addUser(userParam);
    }

    @ApiOperation(value = "注销用户")
    @PostMapping("/deleteUser")
    public void deleteUser(@RequestParam Integer id){
        userService.deleteUser(id);
    }
    @ApiOperation("根据id查询用户信息")
    @PostMapping("/findUserById")
    public List<User> findUserById(@RequestParam Integer id){
        return userService.findUserById(id);
    }

    @ApiOperation("查询所有用户")
    @PostMapping("/findAllUser")
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @ApiOperation("根据用id修改用户信息")
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserParam userParam,@RequestParam Integer id){
        userService.updateUser(userParam,id);
    }
}
