package com.djcao.boot.controller;

import com.djcao.boot.service.YYService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author djcao
 * @workcode BG389966
 * @date 2019/7/13
 */
@RestController
public class PythonCallbackController {


    @Autowired
    private YYService yyService;

    @ApiOperation("鞋子下架回调")
    @RequestMapping("offShelfCallback")
    public Boolean offShelf(@RequestBody String itemId){
        return yyService.offShelf(itemId);
    }
}