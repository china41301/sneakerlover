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
@Table(name = "reservation_registration_result", schema = "sneakerlover", catalog = "")
public class ReservationRegistrationResult {
    private long id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long reservationRegistrationId;
    private Long registerUserId;
    private Integer status;
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
    @Column(name = "reservation_registration_id", nullable = true)
    public Long getReservationRegistrationId() {
        return reservationRegistrationId;
    }

    public void setReservationRegistrationId(Long reservationRegistrationId) {
        this.reservationRegistrationId = reservationRegistrationId;
    }

    @Basic
    @Column(name = "register_user_id", nullable = true)
    public Long getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
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

        ReservationRegistrationResult that = (ReservationRegistrationResult)o;

        if (id != that.id) { return false; }
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) { return false; }
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) { return false; }
        if (reservationRegistrationId != null ? !reservationRegistrationId.equals(that.reservationRegistrationId)
            : that.reservationRegistrationId != null) { return false; }
        if (registerUserId != null ? !registerUserId.equals(that.registerUserId) : that.registerUserId != null) {
            return false;
        }
        if (status != null ? !status.equals(that.status) : that.status != null) { return false; }
        if (ext != null ? !ext.equals(that.ext) : that.ext != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)(id ^ (id >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (reservationRegistrationId != null ? reservationRegistrationId.hashCode() : 0);
        result = 31 * result + (registerUserId != null ? registerUserId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (ext != null ? ext.hashCode() : 0);
        return result;
    }
}
