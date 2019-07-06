package com.djcao.boot.repository;
// Generated 2019-7-6 17:15:19 by Hibernate Tools 5.2.8.Final


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
     private Date updateTime;
     private Date triggerTime;
     private Integer registerUserType;
     private Integer status;
     private Long itemId;
     private Long userId;

    public ReservationRegistration() {
    }

    public ReservationRegistration(Date createTime, Date updateTime, Date triggerTime, Integer registerUserType, Integer status, Long itemId, Long userId) {
       this.createTime = createTime;
       this.updateTime = updateTime;
       this.triggerTime = triggerTime;
       this.registerUserType = registerUserType;
       this.status = status;
       this.itemId = itemId;
       this.userId = userId;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time", length=19)
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="trigger_time", length=19)
    public Date getTriggerTime() {
        return this.triggerTime;
    }
    
    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    
    @Column(name="register_user_type")
    public Integer getRegisterUserType() {
        return this.registerUserType;
    }
    
    public void setRegisterUserType(Integer registerUserType) {
        this.registerUserType = registerUserType;
    }

    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name="item_id")
    public Long getItemId() {
        return this.itemId;
    }
    
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    
    @Column(name="user_id")
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }




}


