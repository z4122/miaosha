package org.seckill.entity;

import java.util.Date;

public class SeckillSuccess {
    private Seckill seckill;

    private long seckillId;

    private long phoneNumber;

    private short state;

    private Date createDate;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SeckillSuccess{" +
                "seckill=" + seckill +
                ", phoneNumber=" + phoneNumber +
                ", state=" + state +
                ", createDate=" + createDate +
                '}';
    }
}
