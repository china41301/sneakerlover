package com.djcao.boot.vo;

import java.util.Date;

import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ShoesItem;

public class ReservationRegistrationVO extends ReservationRegistration {

     private String registerUserName;
     private int signSuccessNumber;
     private ShoesItem shoesItem;

    public ReservationRegistrationVO() {
    }

    public ShoesItem getShoesItem() {
        return shoesItem;
    }

    public void setShoesItem(ShoesItem shoesItem) {
        this.shoesItem = shoesItem;
    }

    public String getRegisterUserName() {
        return registerUserName;
    }

    public void setRegisterUserName(String registerUserName) {
        this.registerUserName = registerUserName;
    }

    public int getSignSuccessNumber() {
        return signSuccessNumber;
    }

    public void setSignSuccessNumber(int signSuccessNumber) {
        this.signSuccessNumber = signSuccessNumber;
    }
}


