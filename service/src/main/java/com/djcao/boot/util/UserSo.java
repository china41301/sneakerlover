package com.djcao.boot.util;

import com.djcao.boot.common.BaseSo;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
public class UserSo extends BaseSo {

    private String account;

    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
