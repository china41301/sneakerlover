package com.djcao.boot.vo;

import java.util.Date;

import com.djcao.boot.repository.ShoesItem;

public class ShoesItemVO extends ShoesItem {


     private Long id;
     private String brand;
     private Date createTime;
     private String image;
     private Byte isSize;
     private String itemId;
     private String model;
     private String name;
     private String registerNum;
     private String shoeSize;
     private String shop;
     private Byte status;
     private Date updateTime;
     private int maxRegisterNumber;


    public ShoesItemVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Byte getIsSize() {
        return isSize;
    }

    public void setIsSize(Byte isSize) {
        this.isSize = isSize;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getMaxRegisterNumber() {
        return maxRegisterNumber;
    }

    public void setMaxRegisterNumber(int maxRegisterNumber) {
        this.maxRegisterNumber = maxRegisterNumber;
    }
}


