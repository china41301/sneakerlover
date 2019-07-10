package com.djcao.boot.service;

import java.util.List;

import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.google.common.collect.Lists;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
public interface YYService {
    PythonResult<List<RegisterUser>> login(List<RegisterUser> registerUser);
}
