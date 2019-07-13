package com.djcao.boot.service;

import java.util.List;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.so.RegisterUserSo;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
public interface RegisterUserService {
    PackageResult<RegisterUser> save(RegisterUser registerUser);

    PackageResult<RegisterUser> update(RegisterUser registerUser);

    PackageResult<Boolean> delete(Long id);

    PackageResult<List<RegisterUser>> importFromExcel(Object obj);

    PackageResult<RegisterUser> findById(Long id) throws Exception;

    PackageResult<List<RegisterUser>> findByUserId(RegisterUserSo registerUserSo);

    PackageResult<List<RegisterUser>> findByUserIds(List<Long> ids);
}
