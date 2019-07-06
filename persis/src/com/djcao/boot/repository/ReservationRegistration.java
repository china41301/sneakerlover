package com.djcao.boot.repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reservation_registration", schema = "sneakerlover", catalog = "")
public class ReservationRegistration {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp triggerTime;
    private Integer registerUserType;
    private Integer status;
    private Long itemId;
    private Long userId;

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

    @Basic
    @Column(name = "item_id", nullable = true)
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRegistration that = (ReservationRegistration) o;
        return id == that.id &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(triggerTime, that.triggerTime) &&
                Objects.equals(registerUserType, that.registerUserType) &&
                Objects.equals(status, that.status) &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, triggerTime, registerUserType, status, itemId, userId);
    }
}
