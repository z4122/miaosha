package org.seckill.entity;

import java.util.Date;

public class Seckill {



    private long seckillId;

    private String name;

    private int number;

    private Date createDate;

    private Date startDate;

    private Date endDate;

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date successDate) {
        this.createDate = successDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", successDate=" + createDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
