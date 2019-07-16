package com.djcao.boot.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.ReservationRegistration;
import com.google.common.collect.Lists;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
public interface YYService {
    PythonResult<List<Map<String,String>>> login(List<RegisterUser> registerUser);

    Boolean offShelf(List<String> itemIdList);

    PythonResult<List<Map<String,String>>> check(List<ReservationRegistration> param);
}
