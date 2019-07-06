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
 * ReservationRegistrationResult generated by hbm2java
 */
@Entity
@Table(name="reservation_registration_result"
    ,catalog="sneakerlover"
)
public class ReservationRegistrationResult  implements java.io.Serializable {


     private Long id;
     private Date createTime;
     private Date updateTime;
     private Long reservationRegistrationId;
     private Long registerUserId;
     private Integer status;
     private String ext;

    public ReservationRegistrationResult() {
    }

    public ReservationRegistrationResult(Date createTime, Date updateTime, Long reservationRegistrationId, Long registerUserId, Integer status, String ext) {
       this.createTime = createTime;
       this.updateTime = updateTime;
       this.reservationRegistrationId = reservationRegistrationId;
       this.registerUserId = registerUserId;
       this.status = status;
       this.ext = ext;
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

    
    @Column(name="reservation_registration_id")
    public Long getReservationRegistrationId() {
        return this.reservationRegistrationId;
    }
    
    public void setReservationRegistrationId(Long reservationRegistrationId) {
        this.reservationRegistrationId = reservationRegistrationId;
    }

    
    @Column(name="register_user_id")
    public Long getRegisterUserId() {
        return this.registerUserId;
    }
    
    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    
    @Column(name="status")
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Column(name="ext")
    public String getExt() {
        return this.ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }




}


