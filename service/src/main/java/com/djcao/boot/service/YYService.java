package com.djcao.boot.service;

import java.util.List;

import com.djcao.boot.repository.RegisterUser;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
public interface YYService {
    List<RegisterUser> login(List<RegisterUser> registerUser);
}
