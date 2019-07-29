package com.djcao.boot.repository;
// Generated 2019-7-30 7:30:24 by Hibernate Tools 5.2.8.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReservationRegistration generated by hbm2java
 */
@Entity
@Table(name="reservation_registration"
    ,catalog="sneakerlover"
)
public class ReservationRegistration  implements java.io.Serializable {


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

    public ReservationRegistration() {
    }

    public ReservationRegistration(Date createTime, String itemId, String registerNum, Long registerUserId, String signNumber, Integer status, String token, Date triggerTime, Date updateTime, Long userId, String yyResult, String shoesSize, Integer shoesShop, String shoesShopName) {
       this.createTime = createTime;
       this.itemId = itemId;
       this.registerNum = registerNum;
       this.registerUserId = registerUserId;
       this.signNumber = signNumber;
       this.status = status;
       this.token = token;
       this.triggerTime = triggerTime;
       this.updateTime = updateTime;
       this.userId = userId;
       this.yyResult = yyResult;
       this.shoesSize = shoesSize;
       this.shoesShop = shoesShop;
       this.shoesShopName = shoesShopName;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", length=19)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
    @Column(name="item_id", length=40)
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    
    @Column(name="register_num")
    public String getRegisterNum() {
        return this.registerNum;
    }
    
    public void setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
    }

    
    @Column(name="register_user_id")
    public Long getRegisterUserId() {
        return this.registerUserId;
    }
    
    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    
    @Column(name="sign_number")
    public String getSignNumber() {
        return this.signNumber;
    }
    
    public void setSignNumber(String signNumber) {
        this.signNumber = signNumber;
    }

    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name="token")
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="trigger_time", length=19)
    public Date getTriggerTime() {
        return this.triggerTime;
    }
    
    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", length=19)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    @Column(name="user_id")
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    @Column(name="yy_result")
    public String getYyResult() {
        return this.yyResult;
    }
    
    public void setYyResult(String yyResult) {
        this.yyResult = yyResult;
    }

    
    @Column(name="shoes_size", length=20)
    public String getShoesSize() {
        return this.shoesSize;
    }
    
    public void setShoesSize(String shoesSize) {
        this.shoesSize = shoesSize;
    }

    
    @Column(name="shoes_shop")
    public Integer getShoesShop() {
        return this.shoesShop;
    }
    
    public void setShoesShop(Integer shoesShop) {
        this.shoesShop = shoesShop;
    }

    
    @Column(name="shoes_shop_name")
    public String getShoesShopName() {
        return this.shoesShopName;
    }
    
    public void setShoesShopName(String shoesShopName) {
        this.shoesShopName = shoesShopName;
    }




}


