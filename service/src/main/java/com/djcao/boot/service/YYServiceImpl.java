package com.djcao.boot.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
@Service
public class YYServiceImpl implements YYService{
    @Autowired
    private RestTemplate restTemplate;

    @Value(value = "${python.host}")
    private String pythonHost = "http://47.111.163.9:5000";
    @Override
    public PythonResult<List<RegisterUser>> login(List<RegisterUser> registerUser) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", registerUser);
        jsonObject.put("code",10086);
        PythonResult<List<RegisterUser>> result = restTemplate.postForObject(pythonHost+"/yy/login", jsonObject,
            PythonResult.class);
        return result;
    }
}
