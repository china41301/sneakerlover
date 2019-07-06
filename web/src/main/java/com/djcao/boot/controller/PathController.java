package com.djcao.boot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
*
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-06-29

*/


@RestController
@RequestMapping("index")
public class PathController {

    @RequestMapping("health")
    public @ResponseBody String health(){
        return "ok";
    }

    @RequestMapping("{path}")
    public String returnUrl(@PathVariable(value = "path")String path){
        return path;
    }
}
