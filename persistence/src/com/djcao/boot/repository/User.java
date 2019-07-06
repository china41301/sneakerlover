package com.djcao.boot.repository;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-02
 */
@Entity
public class User {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String outerId;
    private String userName;
    private String passwd;
    private Byte isVip;
    private String ext;
    private String email;
    private Integer phoneNum;

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
    @Column(name = "outer_id", nullable = true, length = 80)
    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 80)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "passwd", nullable = true, length = 255)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "is_vip", nullable = true)
    public Byte getIsVip() {
        return isVip;
    }

    public void setIsVip(Byte isVip) {
        this.isVip = isVip;
    }

    @Basic
    @Column(name = "ext", nullable = true, length = 255)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
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
    @Column(name = "phone_num", nullable = true)
    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        User user = (User)o;

        if (id != user.id) { return false; }
        if (createTime != null ? !createTime.equals(user.createTime) : user.createTime != null) { return false; }
        if (updateTime != null ? !updateTime.equals(user.updateTime) : user.updateTime != null) { return false; }
        if (outerId != null ? !outerId.equals(user.outerId) : user.outerId != null) { return false; }
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) { return false; }
        if (passwd != null ? !passwd.equals(user.passwd) : user.passwd != null) { return false; }
        if (isVip != null ? !isVip.equals(user.isVip) : user.isVip != null) { return false; }
        if (ext != null ? !ext.equals(user.ext) : user.ext != null) { return false; }
        if (email != null ? !email.equals(user.email) : user.email != null) { return false; }
        if (phoneNum != null ? !phoneNum.equals(user.phoneNum) : user.phoneNum != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)(id ^ (id >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (outerId != null ? outerId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (isVip != null ? isVip.hashCode() : 0);
        result = 31 * result + (ext != null ? ext.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        return result;
    }
}
