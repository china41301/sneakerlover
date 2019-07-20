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
    private String reservationNumber;
    private int activityShopId;
    private String shopName;

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

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public int getActivityShopId() {
        return activityShopId;
    }

    public void setActivityShopId(int activityShopId) {
        this.activityShopId = activityShopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
