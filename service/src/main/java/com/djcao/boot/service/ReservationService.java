package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.User;

import java.util.List;

public interface ReservationService {
    PackageResult<String> registerShoes(RegisterShoesRequest registerShoesRequest, User user) throws Exception;

    PackageResult<List<ReservationRegistration>> findByItemId(String itemId,Integer status);

    PackageResult<List<ReservationRegistration>> findByUserId(Long userId);

}
