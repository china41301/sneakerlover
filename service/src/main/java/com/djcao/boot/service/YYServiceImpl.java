package com.djcao.boot.service;

import java.util.List;

import com.djcao.boot.repository.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public List<RegisterUser> login(List<RegisterUser> registerUser) {

        return null;
    }
}
