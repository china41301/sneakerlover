package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.User;
import com.djcao.boot.util.UserSo;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public interface UserService {

    // 用于登录后find user
    PackageResult<User> findById(Long id);

    //用于登录
  PackageResult<User> login(UserSo so);

}
