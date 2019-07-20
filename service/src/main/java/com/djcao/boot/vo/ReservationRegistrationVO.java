package com.djcao.boot.vo;

import java.util.Date;

import com.djcao.boot.repository.ReservationRegistration;
import com.djcao.boot.repository.ShoesItem;

public class ReservationRegistrationVO {

    private Long id;
    private Date createTime;
    private String itemId;
    private String registerNum;
    private Long registerUserId;
    private String signNumber;
    private Integer status;
    private String token;
    private Date triggerTime;
    private Date updateTime;
    private Long userId;
    private String yyResult;
    private String shoesSize;
    private Integer shoesShop;
    private String shoesShopName;
    private String registerUserName;
    private int signSuccessNumber;
    private ShoesItem shoesItem;

    public ReservationRegistrationVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public String getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(String signNumber) {
        this.signNumber = signNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYyResult() {
        return yyResult;
    }

    public void setYyResult(String yyResult) {
        this.yyResult = yyResult;
    }

    public String getShoesSize() {
        return shoesSize;
    }

    public void setShoesSize(String shoesSize) {
        this.shoesSize = shoesSize;
    }

    public Integer getShoesShop() {
        return shoesShop;
    }

    public void setShoesShop(Integer shoesShop) {
        this.shoesShop = shoesShop;
    }

    public String getShoesShopName() {
        return shoesShopName;
    }

    public void setShoesShopName(String shoesShopName) {
        this.shoesShopName = shoesShopName;
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


