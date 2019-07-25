package com.djcao.boot.service;

import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.GOT_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.LOSS_THEM;
import static com.djcao.boot.common.BusinessStatus.ReservationStatusEnum.SHOES_OFF_LOAD;
import static com.djcao.boot.common.BusinessStatus.ShoesStatusEnum.OVER_RESERVATION;
import static com.djcao.boot.common.CodeDef.YY_LUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.YY_UNLUCKY_NUMBER;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.BusinessStatus.ShoesStatusEnum;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ReservationRegistrationRepository;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.ShoesItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public Boolean shoesOffLoadJobProcess(List<String> itemIdList) {
        try {
            PackageResult<List<ReservationRegistration>> packageResult = reservationService
                .findByItemId(null, SHOES_OFF_LOAD.getStatus());
            PythonResult<List<Map<String, Object>>> pythonResult = yyService
                .check(packageResult.getResult());
            if (!pythonResult.getCode().equals("0") || CollectionUtils
                .isEmpty(packageResult.getResult())) {
                logger.error("");
            }
            packageResult.getResult().stream().forEach(reservationRegistration -> {
                reservationRegistration.setStatus(LOSS_THEM.getStatus());
            });
            Map<Long, ReservationRegistration> collect = packageResult.getResult().stream()
                .collect(Collectors.toMap
                    (ReservationRegistration::getId, Function.identity()));
            pythonResult.getData().forEach(map -> {
                if (null != collect.get(Integer.class.cast(map.get("id")).longValue())) {
                    ReservationRegistration reservationRegistration = collect
                        .get(Integer.class.cast(map.get("id")).longValue());
                    reservationRegistration.setStatus(YY_LUCKY_NUMBER.equals(map.get("state")) ?
                        GOT_THEM.getStatus() :
                        YY_UNLUCKY_NUMBER.equals(map.get("state")) ? LOSS_THEM.getStatus()
                            : SHOES_OFF_LOAD.getStatus());
                    //处理逻辑
                    reservationRegistration.setUpdateTime(new Date());
                }
            });
            reservationRegistrationRepository.saveAll(collect.values());
        } catch (Exception ex) {
            logger.error("处理失败", ex);
        }
        //success;
        return Boolean.TRUE;
    }

    @Override
    public Boolean shoesOffLoadingV2(List<String> itemListId) {
        List<ShoesItem> shoesItems = shoesItemRepository.findAllByItemId(itemListId);
        if (!CollectionUtils.isEmpty(shoesItems)){
            shoesItems.forEach(shoesItem -> {
                shoesItem.setUpdateTime(new Date());
                shoesItem.setStatus(OVER_RESERVATION.getStatus());
            });
            shoesItemRepository.saveAll(shoesItems);
        }

        List<ReservationRegistration> byItemId = reservationRegistrationRepository.findByItemId(itemListId);
        if (!CollectionUtils.isEmpty(byItemId)){
            byItemId.forEach(reservationRegistration -> {
                reservationRegistration.setUpdateTime(new Date());
                reservationRegistration.setStatus(SHOES_OFF_LOAD.getStatus());});
            reservationRegistrationRepository.saveAll(byItemId);
        }

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
