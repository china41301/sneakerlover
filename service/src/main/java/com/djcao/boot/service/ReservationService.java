package com.djcao.boot.service;

import com.djcao.boot.common.BaseSo;
import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.User;
import com.djcao.boot.vo.ReservationRegistrationVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReservationService {
    /**
     * 球鞋登记
     * @param registerShoesRequest
     * @param user
     * @return
     * @throws Exception
     */
    PackageResult<String> registerShoes(List<RegisterShoesRequest> registerShoesRequest, User user) throws Exception;

    /**
     * 登记结果查询
     * @param shoesItemId
     * @param user
     * @return
     * @throws Exception
     */
    PackageResult<JSONObject> registerResultQuery(@RequestParam String shoesItemId, User user) throws Exception;

    PackageResult<List<ReservationRegistration>> findByItemId(String itemId, Integer status);

    PackageResult<List<ReservationRegistration>> findByUserId(BaseSo so,User user);

    PackageResult<List<ReservationRegistrationVO>> getReservationItem(String itemId,User user,BaseSo so);

}
