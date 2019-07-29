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

    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
