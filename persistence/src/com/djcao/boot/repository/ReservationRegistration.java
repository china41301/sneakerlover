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
@Table(name = "reservation_registration", schema = "sneakerlover", catalog = "")
public class ReservationRegistration {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp triggerTime;
    private Integer registerUserType;
    private Integer status;

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
    @Column(name = "trigger_time", nullable = true)
    public Timestamp getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Timestamp triggerTime) {
        this.triggerTime = triggerTime;
    }

    @Basic
    @Column(name = "register_user_type", nullable = true)
    public Integer getRegisterUserType() {
        return registerUserType;
    }

    public void setRegisterUserType(Integer registerUserType) {
        this.registerUserType = registerUserType;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        ReservationRegistration that = (ReservationRegistration)o;

        if (id != that.id) { return false; }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) { return false; }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) { return false; }
        if (triggerTime != null ? !triggerTime.equals(that.triggerTime) : that.triggerTime != null) { return false; }
        if (registerUserType != null ? !registerUserType.equals(that.registerUserType)
            : that.registerUserType != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)(id ^ (id >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (triggerTime != null ? triggerTime.hashCode() : 0);
        result = 31 * result + (registerUserType != null ? registerUserType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
