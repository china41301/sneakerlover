package com.djcao.boot.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.BaseSo;
import com.djcao.boot.common.BusinessStatus;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.dto.RegisterResultResponse;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.dto.YYSignRequest;
import com.djcao.boot.repository.*;
import com.djcao.boot.vo.ReservationRegistrationVO;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.GOT_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.LOSS_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.SHOES_OFF_LOAD;

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

    @PersistenceContext
    private EntityManager em;

    private static final List<Integer> getThenStatus = Lists.newArrayList(GOT_THEM.getStatus(),
        LOSS_THEM.getStatus(),SHOES_OFF_LOAD.getStatus());

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<String> registerShoes(List<RegisterShoesRequest> registerShoesRequests,User user) throws Exception {
        //登记只针对一双鞋子，尺码和商店不同而已
        String shoesItemId = registerShoesRequests.get(0).getShoesItemId();
        //当前登记的账号总数
        int totalReservationNum = 0;
        for(RegisterShoesRequest request : registerShoesRequests) {
            totalReservationNum += request.getReservationNumber();
        }

        List<YYSignRequest> reqsList = new ArrayList<>();
        List<ReservationRegistration> reservationList = new ArrayList<>();
        Map<String,ReservationRegistration> reservationMap =  new HashMap<>();

        //获取能登记当前这双鞋子的账号 （一双鞋子只能成功登记一次）
        List<RegisterUser> registerUsers = registerUserRepository.findNotReservationByUserId(user.getId(), shoesItemId, totalReservationNum);
        if (CollectionUtils.isEmpty(registerUsers) || registerUsers.size() != totalReservationNum) {
            return PackageResult.error(String.format("抢鞋用户数量小于%d,当前剩余可用于抢此鞋用户数量为：%d",
                    totalReservationNum, registerUsers.size()));
        }

        int index = 0;
        for (RegisterShoesRequest registerShoesRequest : registerShoesRequests) {
            int reservationNum = registerShoesRequest.getReservationNumber();
            if (0 == reservationNum) {
                throw new Exception("请选择登记尺码为" + registerShoesRequest.getShoesSize() + "鞋子的用户！");
            }

            while(reservationNum-- > 0) {
                RegisterUser registerUser = registerUsers.get(index);
                index++;
                reqsList.add(new YYSignRequest()
                        .setItemId(String.valueOf(shoesItemId))
                        .setShoesSize(registerShoesRequest.getShoesSize())
                        .setShopId(String.valueOf(registerShoesRequest.getActivityShopId()))
                        .setToken(registerUser.getToken()));

                //先生成所有提交了登记信息的记录，状态都为预约登记失败
                ReservationRegistration reservationRegistration = new ReservationRegistration();
                reservationRegistration.setCreateTime(new Date());
                reservationRegistration.setUpdateTime(new Date());
                reservationRegistration.setItemId(registerShoesRequest.getShoesItemId());
                reservationRegistration.setRegisterUserId(registerUser.getId());
                reservationRegistration.setUserId(user.getId());
                reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_FAIL.getStatus());
                reservationRegistration.setToken(registerUser.getToken());
                reservationRegistration.setShoesSize(registerShoesRequest.getShoesSize());
                reservationRegistration.setShoesShopName(registerShoesRequest.getShopName());
                reservationRegistration.setShoesShop(registerShoesRequest.getActivityShopId());
                reservationMap.put(registerUser.getToken(), reservationRegistration);
            }
        }
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
            //解析预约登记响应体，并将返回的账户设置为登记成功，并设置抽签码
            List<Map<String, String>> responseList = signResult.getData();
            for(Map<String, String> response : responseList) {
                ReservationRegistration reservationRegistration = reservationMap.get(response.get("token"));
                if(!response.get("sign_id").equals("error")) reservationRegistration.setStatus(BusinessStatus.ReservationStatusEnum.RESERVATION_SUCCESS.getStatus());
                reservationRegistration.setSignNumber(response.get("sign_id"));
                reservationList.add(reservationRegistration);
            }
            reservationRegistrationRepository.saveAll(reservationList);
            return PackageResult.success().setResult("完成登记！登记结果请查看详情");
        }
        return PackageResult.error("全部登记失败！");
    }

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<JSONObject> registerResultQuery(@RequestParam String shoesItemId, User user) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RegisterResultResponse> query = cb.createQuery(RegisterResultResponse.class);
        Root<ReservationRegistration> root = query.from(ReservationRegistration.class);

        query.multiselect(root.get("itemId"),
                root.get("shoesSize"),
                root.get("shoesShopName"),
                cb.count(root.get("id").as(Long.class)));
        query.groupBy(root.get("shoesShop"),
                root.get("shoesSize"));
        query.where(cb.and(cb.equal(root.get("itemId").as(String.class), shoesItemId), cb.equal(root.get("userId").as(Long.class), user.getId())));
        //获取全部登记记录
        List<RegisterResultResponse> list = em.createQuery(query).getResultList();

        //获取登记成功的记录
        query.where(cb.equal(root.get("status").as(Integer.class), BusinessStatus.ReservationStatusEnum.RESERVATION_SUCCESS.getStatus()));
        List<RegisterResultResponse> successList = em.createQuery(query).getResultList();

        for(RegisterResultResponse response : list) {
            int index = 0;
            if(!successList.isEmpty()
                    && response.getShoesSize().equals(successList.get(index).getShoesSize())
                    && response.getShopName().equals(successList.get(index).getShopName())) {
                response.setReservationSuccessNumber(successList.get(index).getReservationNumber());
                successList.remove(index);
            } else response.setReservationSuccessNumber(0L);
        }

        return PackageResult.success().setResult(list);
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
                    Integer signSuccessNumberByUserIdAndItemId = reservationRegistrationRepository
                        .countSignSuccessNumberByUserIdAndItemId(user.getId(),
                            reservationRegistration.getItemId(), GOT_THEM.getStatus());
                    reservationRegistrationVO.setSignSuccessNumber(signSuccessNumberByUserIdAndItemId);
                    return reservationRegistrationVO;
                }).collect(Collectors.toList());
        packageResult.setResult(rlt);
        return packageResult;
    }

    @Override
    public PackageResult<List<ReservationRegistrationVO>> getReservationItem(String itemId, User user,BaseSo so) {
        Pageable pageable = PageRequest.of(so.getPageNum(), so.getPageSize(),
                Direction.DESC, "createTime");
        Page all = reservationRegistrationRepository.findByUserIdAndItemId(user.getId(),itemId,
            getThenStatus,pageable);
        PackageResult reservationRegistrationPackageResult
                = new PackageResult().setPage(all);
        if (reservationRegistrationPackageResult.getTotal() <= 0){
            return PackageResult.error("没有登记信息");
        }
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
