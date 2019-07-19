package com.djcao.boot.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import static com.djcao.boot.common.CodeDef.CURRENT_USER;

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
    public PackageResult<User> login(@RequestBody UserSo so, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute(CURRENT_USER);
        if (null != user){
            return PackageResult.success(user);
        }
        PackageResult<User> login = userService.login(so);
        if (login.isSuccess()){
            User result = login.getResult();
            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_USER,result);
        }
        return login;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("save")
    public PackageResult<User> save(@RequestBody User user){
        return userService.save(user);
    }

}
