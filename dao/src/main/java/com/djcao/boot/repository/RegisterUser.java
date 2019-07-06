package com.djcao.boot.repository;
// Generated 2019-7-6 20:01:10 by Hibernate Tools 5.2.8.Final


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
 * RegisterUser generated by hbm2java
 */
@Entity
@Table(name="register_user"
    ,catalog="sneakerlover"
)
public class RegisterUser  implements java.io.Serializable {


     private Long id;
     private Date createTime;
     private Date updateTime;
     private String identityNumber;
     private String phoneNumber;
     private String email;
     private Long userId;
     private Long groupId;
     private String name;
     private String ext;
     private String token;
     private String password;
     private String userName;

    public RegisterUser() {
    }

    public RegisterUser(Date createTime, Date updateTime, String identityNumber, String phoneNumber, String email, Long userId, Long groupId, String name, String ext, String token, String password, String userName) {
       this.createTime = createTime;
       this.updateTime = updateTime;
       this.identityNumber = identityNumber;
       this.phoneNumber = phoneNumber;
       this.email = email;
       this.userId = userId;
       this.groupId = groupId;
       this.name = name;
       this.ext = ext;
       this.token = token;
       this.password = password;
       this.userName = userName;
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

    
    @Column(name="identity_number", length=80)
    public String getIdentityNumber() {
        return this.identityNumber;
    }
    
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    
    @Column(name="phone_number", length=80)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="user_id")
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    @Column(name="group_id")
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="ext")
    public String getExt() {
        return this.ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }

    
    @Column(name="token")
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="user_name")
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }




}


