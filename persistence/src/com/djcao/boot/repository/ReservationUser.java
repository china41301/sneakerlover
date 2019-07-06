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
@Table(name = "reservation_user", schema = "sneakerlover", catalog = "")
public class ReservationUser {
    private Long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long userId;
    private Long groupIdOrRegisterUserId;
    private Long reservationRegistrationId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id_or_register_user_id", nullable = true)
    public Long getGroupIdOrRegisterUserId() {
        return groupIdOrRegisterUserId;
    }

    public void setGroupIdOrRegisterUserId(Long groupIdOrRegisterUserId) {
        this.groupIdOrRegisterUserId = groupIdOrRegisterUserId;
    }

    @Basic
    @Column(name = "reservation_registration_id", nullable = true)
    public Long getReservationRegistrationId() {
        return reservationRegistrationId;
    }

    public void setReservationRegistrationId(Long reservationRegistrationId) {
        this.reservationRegistrationId = reservationRegistrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        ReservationUser that = (ReservationUser)o;

        if (id != null ? !id.equals(that.id) : that.id != null) { return false; }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) { return false; }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) { return false; }
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) { return false; }
        if (groupIdOrRegisterUserId != null ? !groupIdOrRegisterUserId.equals(that.groupIdOrRegisterUserId)
            : that.groupIdOrRegisterUserId != null) { return false; }
        if (reservationRegistrationId != null ? !reservationRegistrationId.equals(that.reservationRegistrationId)
            : that.reservationRegistrationId != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (groupIdOrRegisterUserId != null ? groupIdOrRegisterUserId.hashCode() : 0);
        result = 31 * result + (reservationRegistrationId != null ? reservationRegistrationId.hashCode() : 0);
        return result;
    }
}
