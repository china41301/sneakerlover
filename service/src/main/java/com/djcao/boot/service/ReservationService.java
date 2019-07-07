package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;

import java.util.List;

public interface ReservationService {
    PackageResult<String> registerShoes(String shoesId, String userId, List<String> accountIds) throws Exception;
}
