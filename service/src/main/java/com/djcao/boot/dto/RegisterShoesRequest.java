package com.djcao.boot.dto;

import java.util.List;

import io.swagger.annotations.ApiParam;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-18
 */
public class RegisterShoesRequest {
    private String shoesItemId;
    private String shoesSize;
    private List<Long> accountIds;

    public String getShoesItemId() {
        return shoesItemId;
    }

    public void setShoesItemId(String shoesItemId) {
        this.shoesItemId = shoesItemId;
    }

    public String getShoesSize() {
        return shoesSize;
    }

    public void setShoesSize(String shoesSize) {
        this.shoesSize = shoesSize;
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds) {
        this.accountIds = accountIds;
    }
}
