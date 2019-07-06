package com.djcao.boot.controller;

import java.util.List;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.service.RegisterUserService;
import com.djcao.boot.so.RegisterUserSo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2019-07-06
 */
@CrossOrigin(value = "*")
@RestController
@RequestMapping("registerUser")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("save")
    @ResponseBody
    @ApiOperation("保存抢鞋的用户")
    public PackageResult<RegisterUser> save(@RequestBody RegisterUser registerUser){
        return registerUserService.save(registerUser);
    }

    @PostMapping("update")
    @ResponseBody
    @ApiOperation("更新抢鞋的用户")
    public PackageResult<RegisterUser> update(@RequestBody RegisterUser registerUser){
        return registerUserService.update(registerUser);
    }

    @PostMapping("delete/{id}")
    @ResponseBody
    @ApiOperation("删除抢鞋的用户")
    public PackageResult<Boolean> delete(@PathVariable(name = "id") Long id){
        return registerUserService.delete(id);
    }

    @PostMapping("get/{id}")
    @ResponseBody
    @ApiOperation("根据id查找")
    public PackageResult<RegisterUser> findById(@PathVariable(name = "id") Long id) throws Exception {
        return registerUserService.findById(id);
    }

    @PostMapping("find/{userId}")
    @ResponseBody
    @ApiOperation("根据userId找下登记的用户")
    public PackageResult<List<RegisterUser>> findByUserId(@RequestBody RegisterUserSo registerUserSo){
        return registerUserService.findByUserId(registerUserSo);
    }


}
