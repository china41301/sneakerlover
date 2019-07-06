package com.djcao.boot.controller;

import javax.annotation.Resource;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.User;
import com.djcao.boot.service.UserService;
import com.djcao.boot.util.UserSo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-06-29
 */

@RestController
@RequestMapping("user")
@CrossOrigin(value = "*")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "根据用户id获取用户信息")
    @PostMapping("get/{id}")
    @ResponseBody
    public PackageResult<User> getById(@PathVariable(value = "id")Long id){
        return userService.findById(id);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    @ResponseBody
    public PackageResult<User> login(@RequestBody UserSo so){
        return userService.login(so);
    }

}
