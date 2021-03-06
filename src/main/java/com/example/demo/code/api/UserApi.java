package com.example.demo.code.api;

import com.example.demo.code.api.dto.UserDto;
import com.example.demo.code.api.param.LoginParam;
import com.example.demo.code.api.param.UserParam;
import com.example.demo.code.entity.User;
import com.example.demo.code.service.UserService;
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
@Api(value = "用户接口",description = "邓建明")
public class UserApi {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/registerUser")
    public void registerUser(@RequestBody UserParam userParam) {
        userService.registerUser(userParam);
    }

    @ApiOperation(value = "注销用户")
    @PostMapping("/deleteUser")
    public void deleteUser(@RequestParam Integer id) {
        userService.deleteUser(id);
    }

    @ApiOperation("根据id查询用户信息")
    @PostMapping("/findUserById")
    public List<User> findUserById(@RequestParam Integer id) {
        return userService.findUserById(id);
    }

    @ApiOperation("查询所有用户")
    @PostMapping("/findAllUser")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }

    @ApiOperation("根据用id修改用户信息")
    @PostMapping("/updateUser")
    public void updateUser(@RequestBody UserParam userParam, @RequestParam Integer id) {
        userService.updateUser(userParam, id);
    }

    @ApiOperation("用户登录")
    @PostMapping("/UserLogin")
    public UserDto login(@RequestBody LoginParam loginParam){
        return userService.login(loginParam);
    }
}
