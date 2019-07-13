package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.ReservationRegistration;

import java.util.List;

public interface ReservationService {
    PackageResult<String> registerShoes(String shoesItemId, String shoesSize, String shopId, List<Long> accountIds) throws Exception;

    PackageResult<List<ReservationRegistration>> findByItemId(String itemId,Integer status);

}
