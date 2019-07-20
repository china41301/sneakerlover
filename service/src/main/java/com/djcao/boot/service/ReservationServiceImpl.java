package com.djcao.boot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.BusinessStatus;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.dto.YYSignRequest;
import com.djcao.boot.dto.YYSignResponse;
import com.djcao.boot.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    RestTemplate restTemplate;

    @Value(value = "${python.host}")
    private String pythonHost;

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<String> registerShoes(RegisterShoesRequest registerShoesRequest) throws Exception {
        /*List<YYSignRequest> reqsList = new ArrayList<>();
        List<RegisterUser> registerUsers;
        Map<String,ReservationRegistration> reservationMap =  new HashMap<>();
        List<ReservationRegistration> reservationList = new ArrayList<>();
        if(null == accountIds || accountIds.isEmpty()) {
            throw new Exception("请选择登记账号！");
        }
        registerUsers = registerUserService.findByUserIds(accountIds).getResult();
        registerUsers.forEach(registerUser -> reqsList.add(new YYSignRequest().setItemId(shoesItemId)
                                                                                .setShoesSize(shoesSize)
                                                                                .setShopId(shopId)
                                                                                .setToken(registerUser.getToken())));
        JSONObject json = new JSONObject();
        json.put("data", reqsList);
        json.put("code", 10086);
        PythonResult<List<YYSignResponse>> signResult;
        try {
            signResult = restTemplate.postForObject(pythonHost + "/yy/sign", json, PythonResult.class);
        } catch (RestClientException restClientException) {
            throw new Exception("和东哥的预约登记接口交互出错，请求失败");
        }

        if(null != signResult && signResult.getCode().equals("0") && signResult.getError_msg().equals("success")) {
            //先生成所有提交了登记信息的记录，状态都为预约登记失败
            for(RegisterUser registerUser : registerUsers) {
                ReservationRegistration reservationRegistration = new ReservationRegistration();
                reservationRegistration.setItemId(new Long(shoesItemId));
                reservationRegistration.setRegisterUserId(registerUser.getId());
                reservationRegistration.setUserId(registerUser.getUserId());
                reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_FAIL.getStatus());
                reservationRegistration.setToken(registerUser.getToken());
                reservationMap.put(registerUser.getToken(),reservationRegistration);
            }
            //解析预约登记响应体，并将返回的账户设置为登记成功，并设置抽签码
            List<YYSignResponse> responseList = signResult.getData();
            for(YYSignResponse response : responseList) {
                ReservationRegistration reservationRegistration = reservationMap.get(response.getToken());
                reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_SUCCESS.getStatus());
                reservationRegistration.setSignNumber(response.getSign_id());
                reservationMap.put(response.getToken(), reservationRegistration);
            }
            for (Map.Entry<String,ReservationRegistration> entry : reservationMap.entrySet()) {
                reservationList.add(entry.getValue());
            }
            List<ReservationRegistration> result = reservationRegistrationRepository.saveAll(reservationList);
            return PackageResult.success().setResult(result);
        }*/
        return PackageResult.error("和东哥的预约登记接口交互成功，但是执行错误");
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
        List<ReservationRegistration> all = reservationRegistrationRepository.findAll(specification);
        return PackageResult.success(all);
    }

    @Override
    public PackageResult<List<ReservationRegistration>> findByUserId(Long userId) {
        if (userId == null)
            return PackageResult.error("userId不能为空");
        Specification<ReservationRegistration> specification = new Specification<ReservationRegistration>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<ReservationRegistration> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.equal(root.get("user_id").as(Long.class), userId));
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
        List<ReservationRegistration> all = reservationRegistrationRepository.findAll(specification,Sort.by("create_time","desc"));
        return PackageResult.success(all);
    }
}
