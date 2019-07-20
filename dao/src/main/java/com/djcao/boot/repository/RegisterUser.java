package com.djcao.boot.repository;
// Generated 2019-7-20 11:22:30 by Hibernate Tools 5.2.8.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * RegisterUser generated by hbm2java
 */
@Entity
@Table(name="register_user"
    ,catalog="sneakerlover"
    , uniqueConstraints = @UniqueConstraint(columnNames="user_name") 
)
public class RegisterUser  implements java.io.Serializable {


     private Long id;
     private Date createTime;
     private String email;
     private String ext;
     private Long groupId;
     private String identityNumber;
     private String name;
     private String password;
     private String phoneNumber;
     private Integer status;
     private String token;
     private Date updateTime;
     private Long userId;
     private String userName;

    public RegisterUser() {
    }

    public RegisterUser(Date createTime, String email, String ext, Long groupId, String identityNumber, String name, String password, String phoneNumber, Integer status, String token, Date updateTime, Long userId, String userName) {
       this.createTime = createTime;
       this.email = email;
       this.ext = ext;
       this.groupId = groupId;
       this.identityNumber = identityNumber;
       this.name = name;
       this.password = password;
       this.phoneNumber = phoneNumber;
       this.status = status;
       this.token = token;
       this.updateTime = updateTime;
       this.userId = userId;
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

    
    @Column(name="email")
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="ext")
    public String getExt() {
        return this.ext;
    }
    
    public void setExt(String ext) {
        this.ext = ext;
    }

    
    @Column(name="group_id")
    public Long getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    
    @Column(name="identity_number", length=80)
    public String getIdentityNumber() {
        return this.identityNumber;
    }
    
    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="phone_number", length=80)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    
    @Column(name="user_name", unique=true, length=80)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }




}


