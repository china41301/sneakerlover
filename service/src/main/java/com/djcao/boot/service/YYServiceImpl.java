package com.djcao.boot.service;

import java.util.Date;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import static com.djcao.boot.common.CodeDef.BE_THE_LUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.BE_THE_UNLUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.YY_LUCKY_NUMBER;

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

    Logger logger = LoggerFactory.getLogger(YYService.class);

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

    /*@Override
    public Boolean offShelf(List<String> itemIdList) {
        for (String itemId : itemIdList){
            try {
                PackageResult<List<ReservationRegistration>> packageResult = reservationService
                    .findByItemId(itemId, 1);
                PythonResult<List<Map<String,String>>> pythonResult = check(packageResult.getResult());
                if (!pythonResult.getCode().equals("0") || CollectionUtils.isEmpty(packageResult.getResult())){
                    logger.error("");
                    continue;
                }

                Map<Long, ReservationRegistration> collect = packageResult.getResult().stream()
                    .collect(Collectors.toMap
                        (ReservationRegistration::getId, Function.identity()));
                pythonResult.getData().forEach(map -> {
                    if (null != collect.get(Long.valueOf(map.get("id")))){
                        ReservationRegistration reservationRegistration = collect.get(Long.valueOf(map.get
                            ("id")));
                        reservationRegistration.setStatus(YY_LUCKY_NUMBER.equals(map.get("state")) ? BE_THE_LUCKY_NUMBER : BE_THE_UNLUCKY_NUMBER);
                        //处理逻辑
                        reservationRegistration.setUpdateTime(new Date());
                    }
                });
                reservationRegistrationRepository.saveAll(collect.values());
            }catch (Exception ex){
                logger.error("处理失败",ex);
            }
        }
        //success;
        return Boolean.TRUE;
    }*/


    @Override
    public PythonResult<List<Map<String,Object>>> check(List<ReservationRegistration> param){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", param);
        jsonObject.put("code",10086);
        PythonResult<List<Map<String,Object>>> pythonResult = restTemplate.postForObject(pythonHost+ YY_CHECK,
            jsonObject,
            PythonResult.class);
        return pythonResult;
    }

}
