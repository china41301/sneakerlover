package com.djcao.boot.repository;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-02
 */
@Entity
@Table(name = "register_user", schema = "sneakerlover", catalog = "")
public class RegisterUser {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String identityNumber;
    private String phoneNumber;
    private String email;
    private Long userId;
    private Long groupId;
    private String name;
    private String ext;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "identity_number", nullable = true, length = 80)
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 80)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ext", nullable = true, length = 255)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        RegisterUser that = (RegisterUser)o;

        if (id != that.id) { return false; }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) { return false; }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) { return false; }
        if (identityNumber != null ? !identityNumber.equals(that.identityNumber) : that.identityNumber != null) {
            return false;
        }
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) { return false; }
        if (email != null ? !email.equals(that.email) : that.email != null) { return false; }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) { return false; }
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) { return false; }
        if (name != null ? !name.equals(that.name) : that.name != null) { return false; }
        if (ext != null ? !ext.equals(that.ext) : that.ext != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)(id ^ (id >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (identityNumber != null ? identityNumber.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ext != null ? ext.hashCode() : 0);
        return result;
    }
}
