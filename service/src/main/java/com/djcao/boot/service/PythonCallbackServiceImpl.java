package com.djcao.boot.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ReservationRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.djcao.boot.common.CodeDef.BE_THE_LUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.BE_THE_UNLUCKY_NUMBER;
import static com.djcao.boot.common.CodeDef.YY_LUCKY_NUMBER;

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

    @Override
    public Boolean shoesOffLoadingV1(List<String> itemIdList) {
        for (String itemId : itemIdList){
            try {
                PackageResult<List<ReservationRegistration>> packageResult = reservationService
                    .findByItemId(itemId, 1);
                PythonResult<List<Map<String,String>>> pythonResult = yyService.check(packageResult.getResult());
                if (!pythonResult.getCode().equals("0") || CollectionUtils.isEmpty(packageResult.getResult())){
                    logger.error("");
                    continue;
                }

                Map<Long, ReservationRegistration> collect = packageResult.getResult().stream()
                    .collect(Collectors.toMap
                        (ReservationRegistration::getId, Function.identity()));
                pythonResult.getData().forEach(map -> {
                    if (null != collect.get(Long.valueOf(map.get("id")))){
                        ReservationRegistration reservationRegistration = collect.get(Long.valueOf(map.get
                            ("id")));
                        reservationRegistration.setStatus(YY_LUCKY_NUMBER.equals(map.get("state")) ? BE_THE_LUCKY_NUMBER : BE_THE_UNLUCKY_NUMBER);
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
        return null;
    }
}
