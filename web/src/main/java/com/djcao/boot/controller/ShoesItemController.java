package com.djcao.boot.controller;

import com.djcao.boot.common.CodeDef;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.User;
import com.djcao.boot.service.ShoesItemService;
import com.djcao.boot.vo.ShoesItemVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("shoes")
@CrossOrigin(value = "*")
public class ShoesItemController {
    @Resource
    private ShoesItemService shoesItemService;

    @ApiOperation(value = "获取鞋子的详细信息")
    @GetMapping("detail/{id}")
    @ResponseBody
    public PackageResult<ShoesItemVO> getShoesItemDetail(@PathVariable(name = "id") Long id, HttpServletRequest request) throws Exception {
        return shoesItemService.getShoesItemById(id,(User)request.getSession().getAttribute(CodeDef.CURRENT_USER));
    }

    @ApiOperation(value = "获取所有鞋子的列表")
    @GetMapping("list")
    @ResponseBody
    public PackageResult<List<ShoesItem>> getShoesItemList(int pageNo, int pageSize) {
        return shoesItemService.getShoesItem(pageNo, pageSize);
    }

    @ApiOperation(value = "加入上新的鞋子（设置status为1）")
    @PostMapping("add/new")
    @ResponseBody
    public PackageResult<List<ShoesItem>> addNewShoesItem(@RequestBody List<ShoesItem> shoesItems) throws Exception {
        return shoesItemService.addNewShoesItem(shoesItems);
    }
}
