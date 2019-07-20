package com.djcao.boot.controller;

import com.djcao.boot.common.BaseSo;
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

    @ApiOperation(value = "中签查询列表")
    @PostMapping("find/{userId}")
    public PackageResult<List<ReservationRegistration>> findByUserId(){
        return reservationService.findByUserId(1L);
    }

    @ApiOperation(value = "球鞋中签详情")
    @PostMapping("get/{itemId}")
    public PackageResult<List<ReservationRegistration>> getSignItem(@PathVariable(name = "itemId") @ApiParam("球鞋id") Long itemId){
        return reservationService.findByUserId(itemId);
    }
}
