package com.djcao.boot.so;

import com.djcao.boot.common.BaseSo;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
public class RegisterUserSo extends BaseSo {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
