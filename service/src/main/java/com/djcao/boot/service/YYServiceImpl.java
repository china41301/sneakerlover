package com.djcao.boot.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ReservationRegistrationRepository;
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
    public static final String YY_LOGIN = "/yy/login";

    public static final String YY_CHECK = "/yy/check";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Value(value = "${python.host}")
    private String pythonHost = "http://47.111.163.9:5000";
    @Override
    public PythonResult<List<Map<String,String>>> login(List<RegisterUser> registerUser) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", registerUser);
        jsonObject.put("code",10086);
        PythonResult<List<Map<String,String>>> result = restTemplate.postForObject(pythonHost+ YY_LOGIN, jsonObject,
            PythonResult.class);
        return result;
    }

    @Override
    public Boolean offShelf(String itemId) {
        PackageResult<List<ReservationRegistration>> packageResult = reservationService
            .findByItemId(itemId, 1);
        PythonResult<List<JSONObject>> pythonResult = check(packageResult.getResult());
        if (!pythonResult.getCode().equals("1")){
            //todo 如何补偿
            return false;
        }

        Map<Long, ReservationRegistration> collect = packageResult.getResult().stream()
            .collect(Collectors.toMap
                (ReservationRegistration::getId, Function.identity()));
        pythonResult.getData().stream().forEach(jsonObject -> {
            if (null != collect.get(jsonObject.getLong("id"))){
                ReservationRegistration reservationRegistration = collect.get(jsonObject.getLong
                    ("id"));
                //处理逻辑
            }
        });
        reservationRegistrationRepository.saveAll(collect.values());
        //success;
        return Boolean.TRUE;
    }


    @Override
    public PythonResult<List<JSONObject>> check(List<ReservationRegistration> param){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", param);
        jsonObject.put("code",10086);
        PythonResult<List<JSONObject>> pythonResult = restTemplate.postForObject(pythonHost+ YY_CHECK,
            jsonObject,
            PythonResult.class);
        return pythonResult;
    }

}
