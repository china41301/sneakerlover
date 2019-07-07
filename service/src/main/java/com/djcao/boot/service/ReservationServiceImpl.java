package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.*;
import com.djcao.boot.so.RegisterUserSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    private ReservationUserRepository reservationUserRepository;

    @Autowired
    private ShoesItemService shoesItemService;

    @Autowired
    private RegisterUserService registerUserService;

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<String> registerShoes(String shoesId, String userId, List<String> accountIds) throws Exception {
        ShoesItem shoesItem = shoesItemService.getShoesItemById(new Long(shoesId)).getResult();
        List<String> tokens = new ArrayList<>();
        List<RegisterUser> registerUsers = new ArrayList<>();
        if(null == accountIds || accountIds.isEmpty()) {
            RegisterUserSo registerUserSo = new RegisterUserSo();
            registerUserSo.setUserId(new Long(userId));
            registerUsers = registerUserService.findByUserId(registerUserSo).getResult();
        } else {
            //TODO 根据accountIds获取registerUsers
        }
        //TODO 登记记录入库逻辑，以及python登记接口交互逻辑
        registerUsers.forEach(registerUser -> tokens.add(registerUser.getToken()));
        return PackageResult.success();
    }
}
