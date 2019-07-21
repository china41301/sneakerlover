package com.djcao.boot.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.BusinessStatus;
import com.djcao.boot.common.BusinessStatus.ShoesStatusEnum;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ReservationRegistrationRepository;
import com.djcao.boot.repository.ShoesItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.GOT_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.LOSS_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.RESERVATION_SUCCESS;
import static com.djcao.boot.common.BusinessStatus.ShoesStatusEnum.OVER_RESERVATION;
import static com.djcao.boot.common.CodeDef.BE_THE_LUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.BE_THE_UNLUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.YY_LUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.YY_UNLUCKY_NUMBER;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-11
 */
@Service
public class PythonCallbackServiceImpl implements PythonCallbackService{


    Logger logger = LoggerFactory.getLogger(PythonCallbackService.class);


    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    private YYService yyService;

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Override
    public Boolean shoesOffLoadingV1(List<String> itemIdList) {
        for (String itemId : itemIdList){
            try {
                PackageResult<List<ReservationRegistration>> packageResult = reservationService
                    .findByItemId(itemId, RESERVATION_SUCCESS.getStatus());
                PythonResult<List<Map<String,String>>> pythonResult = yyService.check(packageResult.getResult());
                if (!pythonResult.getCode().equals("0") || CollectionUtils.isEmpty(packageResult.getResult())){
                    logger.error("");
                    continue;
                }
                packageResult.getResult().stream().forEach(reservationRegistration -> {reservationRegistration.setStatus(LOSS_THEM.getStatus());});
                Map<Long, ReservationRegistration> collect = packageResult.getResult().stream().collect(Collectors.toMap
                        (ReservationRegistration::getId, Function.identity()));
                pythonResult.getData().forEach(map -> {
                    if (null != collect.get(Long.valueOf(map.get("id")))){
                        ReservationRegistration reservationRegistration = collect.get(Long.valueOf(map.get
                            ("id")));
                        reservationRegistration.setStatus(YY_LUCKY_NUMBER.equals(map.get("state")) ? GOT_THEM.getStatus() : YY_UNLUCKY_NUMBER.equals(map.get("state")) ? LOSS_THEM.getStatus()
                        : RESERVATION_SUCCESS.getStatus());
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
    }

    @Override
    public Boolean shoesOffLoadingV2(List<String> itemListId) {
        List<Long> collect = itemListId.stream().map(Long::valueOf).collect(Collectors.toList());
        List<ReservationRegistration> byItemId = reservationRegistrationRepository.findByItemId(collect);
        byItemId.forEach(reservationRegistration -> {
            reservationRegistration.setUpdateTime(new Date());
            reservationRegistration.setStatus(OVER_RESERVATION.getStatus());});
        reservationRegistrationRepository.saveAll(byItemId);
        logger.info("itemIdList : {}, result : {}",itemListId.toString(), JSONObject.toJSON(byItemId));
        return Boolean.TRUE;
    }

    @Override
    public Boolean shoesOffLoadingV3(List<String> itemListId) {
        int i = shoesItemRepository.updateStatusByItemIdList(itemListId, ShoesStatusEnum.OVER_RESERVATION.getStatus());
        logger.info("itemIdList : {}, result size: {}",itemListId.toString(), i);
        return Boolean.TRUE;
    }

}
