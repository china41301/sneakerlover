package com.djcao.boot.repository;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reservation_user", schema = "sneakerlover", catalog = "")
public class ReservationUser {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long userId;
    private Long groupIdOrRegisterUserId;
    private Long reservationRegistrationId;
    private Byte isSuccess;
    private String signNumber;
    private String token;

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

    @Basic
    @Column(name = "is_success", nullable = true)
    public Byte getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Byte isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Basic
    @Column(name = "sign_number", nullable = true, length = 255)
    public String getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(String signNumber) {
        this.signNumber = signNumber;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 255)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationUser that = (ReservationUser) o;
        return id == that.id &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(groupIdOrRegisterUserId, that.groupIdOrRegisterUserId) &&
                Objects.equals(reservationRegistrationId, that.reservationRegistrationId) &&
                Objects.equals(isSuccess, that.isSuccess) &&
                Objects.equals(signNumber, that.signNumber) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, userId, groupIdOrRegisterUserId, reservationRegistrationId, isSuccess, signNumber, token);
    }
}
