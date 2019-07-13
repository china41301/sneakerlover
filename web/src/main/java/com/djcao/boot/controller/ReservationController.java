package com.djcao.boot.controller;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public PackageResult<String> registerShoes(@RequestParam @ApiParam("鞋子id") String shoesId,
                                               @RequestParam(required = false) @ApiParam("用户选择登记的账号id集合") List<String> accountIds) throws Exception {
        return reservationService.registerShoes(shoesId, accountIds);
    }
}
