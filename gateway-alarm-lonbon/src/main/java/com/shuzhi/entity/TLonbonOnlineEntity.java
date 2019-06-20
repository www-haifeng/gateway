package com.shuzhi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by ztt on 2019/6/17
 **/
@Entity
@Table(name = "t_lonbon_online", schema = "public", catalog = "gateways")
public class TLonbonOnlineEntity {
    private long id;
    private int terminalId;
    private String state;
    private Timestamp createTime;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "terminal_id")
    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLonbonOnlineEntity that = (TLonbonOnlineEntity) o;
        return id == that.id &&
                terminalId == that.terminalId &&
                Objects.equals(state, that.state) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, terminalId, state, createTime);
    }
}
