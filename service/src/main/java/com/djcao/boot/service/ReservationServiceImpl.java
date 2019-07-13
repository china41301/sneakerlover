package com.djcao.boot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.*;
import com.djcao.boot.so.RegisterUserSo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

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

    @Override
    public PackageResult<List<ReservationRegistration>> findByItemId(String itemId,Integer status) {
        Specification<ReservationRegistration> specification = new Specification<ReservationRegistration>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ReservationRegistration> root, CriteriaQuery<?> query,
                CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                if (!StringUtils.isEmpty(itemId)) {
                    list.add(cb.equal(root.get("item_id").as(String.class), itemId));
                }
                if (null != status){
                    list.add(cb.equal(root.get("status").as(Integer.class), status));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        reservationRegistrationRepository.findAll();
        return null;
    }
}
