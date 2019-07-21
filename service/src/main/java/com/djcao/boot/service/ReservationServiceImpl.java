package com.djcao.boot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.BaseSo;
import com.djcao.boot.common.BusinessStatus;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.dto.YYSignRequest;
import com.djcao.boot.dto.YYSignResponse;
import com.djcao.boot.repository.*;
import com.djcao.boot.vo.ReservationRegistrationVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.GOT_THEM;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value(value = "${python.host}")
    private String pythonHost;

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<String> registerShoes(RegisterShoesRequest registerShoesRequest,User user) throws Exception {
        List<YYSignRequest> reqsList = new ArrayList<>();
        List<RegisterUser> registerUsers;
        Map<String,ReservationRegistration> reservationMap =  new HashMap<>();
        List<ReservationRegistration> reservationList = new ArrayList<>();
        if(0 == registerShoesRequest.getReservationNumber()) {
            throw new Exception("请选择登记用户！");
        }
        registerUsers = registerUserRepository.findNotReservationByUserId(user.getId(),registerShoesRequest.getShoesItemId(),registerShoesRequest.getReservationNumber());
        if (CollectionUtils.isEmpty(registerUsers) || registerUsers.size() != registerShoesRequest.getReservationNumber()){
            return PackageResult.error(String.format("抢鞋用户数量小于%d,当前剩余可用于抢此鞋用户数量为：%d",
                registerShoesRequest.getReservationNumber(), registerUsers.size()));
        }
        registerUsers.forEach(registerUser -> reqsList.add(new YYSignRequest().setItemId(String.valueOf(registerShoesRequest.getShoesItemId()))
                                                                                .setShoesSize(registerShoesRequest.getShoesSize())
                                                                                .setShopId(String.valueOf(registerShoesRequest.getActivityShopId()))
                                                                                .setToken(registerUser.getToken())));
        JSONObject json = new JSONObject();
        json.put("data", reqsList);
        json.put("code", 10086);
        PythonResult<List<Map<String, String>>> signResult;
        try {
            signResult = restTemplate.postForObject(pythonHost + "/yy/sign", json, PythonResult.class);
        } catch (RestClientException restClientException) {
            throw new Exception("和东哥的预约登记接口交互出错，请求失败");
        }

        if(null != signResult && signResult.getCode().equals("0") && signResult.getError_msg().equals("success")) {
            //先生成所有提交了登记信息的记录，状态都为预约登记失败
            for(RegisterUser registerUser : registerUsers) {
                ReservationRegistration reservationRegistration = new ReservationRegistration();
                reservationRegistration.setItemId(registerShoesRequest.getShoesItemId());
                reservationRegistration.setUpdateTime(new Date());
                reservationRegistration.setCreateTime(new Date());
                reservationRegistration.setRegisterUserId(registerUser.getId());
                reservationRegistration.setRegisterUserId(registerUser.getUserId());
                reservationRegistration.setUserId(user.getId());
                reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_FAIL.getStatus());
                reservationRegistration.setToken(registerUser.getToken());
                reservationRegistration.setShoesSize(registerShoesRequest.getShoesSize());
                reservationRegistration.setShoesShopName(registerShoesRequest.getShopName());
                reservationRegistration.setShoesShop(registerShoesRequest.getActivityShopId());
                reservationMap.put(registerUser.getToken(),reservationRegistration);
            }
            //解析预约登记响应体，并将返回的账户设置为登记成功，并设置抽签码
            List<Map<String, String>> responseList = signResult.getData();
            for(Map<String, String> response : responseList) {
                ReservationRegistration reservationRegistration = reservationMap.get(response.get("token"));
                reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_SUCCESS.getStatus());
                reservationRegistration.setSignNumber(response.get("sign_id"));
                reservationMap.put(response.get("token"), reservationRegistration);
            }
            for (Map.Entry<String,ReservationRegistration> entry : reservationMap.entrySet()) {
                reservationList.add(entry.getValue());
            }
            List<ReservationRegistration> result = reservationRegistrationRepository.saveAll(reservationList);
            return PackageResult.success().setResult(result);
        }
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
                    list.add(cb.equal(root.get("itemId").as(String.class), itemId));
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
    public PackageResult<List<ReservationRegistration>> findByUserId(BaseSo so,User user) {
        Pageable pageable = PageRequest.of(so.getPageNum(), so.getPageSize(),
            Direction.DESC, "createTime");
        Page all = reservationRegistrationRepository.findByUserId(user.getId(),pageable);
        PackageResult packageResult = new PackageResult<>().setPage(all);
        List<ReservationRegistrationVO> rlt = ((List<ReservationRegistration>)packageResult.getResult()).stream()
            .map(reservationRegistration -> {
                ReservationRegistrationVO reservationRegistrationVO = new ReservationRegistrationVO();
                BeanUtils.copyProperties(reservationRegistration, reservationRegistrationVO);
                ShoesItem oneByItemId = shoesItemRepository.findOneByItemId(reservationRegistration.getItemId());
                reservationRegistrationVO.setShoesItem(oneByItemId);
                return reservationRegistrationVO;
            }).collect(Collectors.toList());
        packageResult.setResult(rlt);
        return packageResult;
    }

    @Override
    public PackageResult<List<ReservationRegistrationVO>> getReservationItem(String itemId, User user,BaseSo so) {
        Pageable pageable = PageRequest.of(so.getPageNum(), so.getPageSize(),
            Direction.DESC, "createTime");
        Page all = reservationRegistrationRepository.findByUserIdAndItemId(user.getId(),itemId,pageable);
        PackageResult reservationRegistrationPackageResult
            = new PackageResult().setPage(all);
        List<ReservationRegistrationVO> rlt = ((List<ReservationRegistration>)reservationRegistrationPackageResult
            .getResult()).stream().map(reservationRegistration -> {
            ReservationRegistrationVO reservationRegistrationVO = new ReservationRegistrationVO();
            BeanUtils.copyProperties(reservationRegistration, reservationRegistrationVO);
            Integer signSuccessNumberByUserIdAndItemId = reservationRegistrationRepository
                .countSignSuccessNumberByUserIdAndItemId(user.getId(),
                    itemId, GOT_THEM.getStatus());
            reservationRegistrationVO.setSignSuccessNumber(signSuccessNumberByUserIdAndItemId);
            ShoesItem oneByItemId = shoesItemRepository.findOneByItemId(itemId);
            reservationRegistrationVO.setShoesItem(oneByItemId);
            Optional<RegisterUser> byId = registerUserRepository.findById(reservationRegistration.getRegisterUserId());
            reservationRegistrationVO.setRegisterUserName(byId.get().getName());
            return reservationRegistrationVO;
        }).collect(Collectors.toList());
        reservationRegistrationPackageResult.setResult(rlt);
        return reservationRegistrationPackageResult;
    }
}
