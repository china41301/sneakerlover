package com.djcao.boot.controller;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.User;
import com.djcao.boot.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.djcao.boot.common.CodeDef.CURRENT_USER;

/**
 * 预约登记和抽签的逻辑
 */
@RestController
@RequestMapping("reservation")
@CrossOrigin(value = "*")
public class ReservationController {

    @Resource
    private ReservationService reservationService;

    @ApiOperation(value = "球鞋登记")
    @PostMapping("register")
    @ResponseBody
    public PackageResult<String> registerShoes(@RequestBody RegisterShoesRequest registerShoesRequest,HttpServletRequest request
    ) throws Exception {
        return reservationService.registerShoes(registerShoesRequest,(User)request.getSession().getAttribute(CURRENT_USER));
    }

    @ApiOperation(value = "登记查询接口")
    @PostMapping("find/{userId}")
    public PackageResult<List<ReservationRegistration>> findByUserId(@PathVariable(name = "useId") @ApiParam("登录用户的id") Long userId){
        return reservationService.findByUserId(userId);
    }
}
