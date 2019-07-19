package com.djcao.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.djcao.boot.common.CodeDef.TEST_SESSION;

/*
*
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-06-29

*/


@RestController
@RequestMapping("index")
public class PathController {

    @GetMapping("health")
    public @ResponseBody String health(){
        return "ok";
    }

    @GetMapping("{path}")
    public String returnUrl(@PathVariable(value = "path")String path){
        return path;
    }

    @GetMapping("session")
    public String session(@RequestParam String words, HttpServletRequest request){
        request.getSession().setAttribute(TEST_SESSION,words);
        return words;
    }

    @GetMapping("get")
    public String getSession(HttpServletRequest request){
        return request.getSession().getAttribute(TEST_SESSION).toString();
    }
}
