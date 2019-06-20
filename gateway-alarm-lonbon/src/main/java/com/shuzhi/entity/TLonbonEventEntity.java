package com.shuzhi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by ztt on 2019/6/17
 **/
@Entity
@Table(name = "t_lonbon_event", schema = "public", catalog = "gateways")
public class TLonbonEventEntity {
    private long id;
    private Integer eventId;
    private Integer sender;
    private Integer receiver;
    private String acceptBc;
    private String sessionId;
    private Integer broadId;
    private String rdFile;
    private Integer atmNum;
    private Integer state;
    private Timestamp createTime;
    private Timestamp uploadTime;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="t_lonbon_event_id_seq")
    @SequenceGenerator(sequenceName="t_lonbon_event_id_seq", name="t_lonbon_event_id_seq",allocationSize = 1)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "event_id")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "sender")
    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "receiver")
    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "accept_bc")
    public String getAcceptBc() {
        return acceptBc;
    }

    public void setAcceptBc(String acceptBc) {
        this.acceptBc = acceptBc;
    }

    @Basic
    @Column(name = "session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "broad_id")
    public Integer getBroadId() {
        return broadId;
    }

    public void setBroadId(Integer broadId) {
        this.broadId = broadId;
    }

    @Basic
    @Column(name = "rd_file")
    public String getRdFile() {
        return rdFile;
    }

    public void setRdFile(String rdFile) {
        this.rdFile = rdFile;
    }

    @Basic
    @Column(name = "atm_num")
    public Integer getAtmNum() {
        return atmNum;
    }

    public void setAtmNum(Integer atmNum) {
        this.atmNum = atmNum;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "upload_time")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLonbonEventEntity that = (TLonbonEventEntity) o;
        return id == that.id &&
                Objects.equals(eventId, that.eventId) &&
                Objects.equals(sender, that.sender) &&
                Objects.equals(receiver, that.receiver) &&
                Objects.equals(acceptBc, that.acceptBc) &&
                Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(broadId, that.broadId) &&
                Objects.equals(rdFile, that.rdFile) &&
                Objects.equals(atmNum, that.atmNum) &&
                Objects.equals(state, that.state) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(uploadTime, that.uploadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, sender, receiver, acceptBc, sessionId, broadId, rdFile, atmNum, state, createTime, uploadTime);
    }
}
