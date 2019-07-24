package com.djcao.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.BaseSo;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.User;
import com.djcao.boot.service.ReservationService;
import com.djcao.boot.vo.ReservationRegistrationVO;
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
    public PackageResult<String> registerShoes(@RequestBody List<RegisterShoesRequest> registerShoesRequest,HttpServletRequest request
    ) throws Exception {
        return reservationService.registerShoes(registerShoesRequest, (User)request.getSession().getAttribute(CURRENT_USER));
    }

    @ApiOperation(value = "登记结果查询")
    @PostMapping("registerResult/query")
    @ResponseBody
    public PackageResult<JSONObject> registerResultQuery(@RequestParam String shoesItemId, HttpServletRequest request
    ) throws Exception {
        return reservationService.registerResultQuery(shoesItemId, (User)request.getSession().getAttribute(CURRENT_USER));
    }

    @ApiOperation(value = "中签查询列表")
    @PostMapping("find/{userId}")
    public PackageResult<List<ReservationRegistration>> findByUserId(@RequestBody BaseSo so,HttpServletRequest request){
        return reservationService.findByUserId(so,(User)request.getSession().getAttribute(CURRENT_USER));
    }

    @ApiOperation(value = "球鞋中签详情")
    @PostMapping("get/{itemId}")
    public PackageResult<List<ReservationRegistrationVO>> getSignItem(@PathVariable(name = "itemId") @ApiParam("球鞋的itemId,非主键是就叫itemId") String itemId,@RequestBody BaseSo so, HttpServletRequest request){
        return reservationService.getReservationItem(itemId,(User) request.getSession().getAttribute(CURRENT_USER),so);
    }
}
