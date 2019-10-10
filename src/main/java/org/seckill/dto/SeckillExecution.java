package org.seckill.dto;


import org.seckill.entity.SeckillSuccess;
import org.seckill.enums.SeckillStateEnums;

/**
 * 封装执行的结果
 */
public class SeckillExecution {
    private long seckillId;

    //执行的结果状态
    private int state;

    //状态表示
    private String stateInfo;

    //秒杀成功的对象
    private SeckillSuccess seckillSuccess;

    public SeckillExecution(long seckillId, SeckillStateEnums seckillStateEnums, SeckillSuccess seckillSuccess) {
        this.seckillId = seckillId;
        this.state = seckillStateEnums.getState();
        this.stateInfo = seckillStateEnums.getStateInfo();
        this.seckillSuccess = seckillSuccess;
    }

    public SeckillExecution(long seckillId, SeckillStateEnums seckillStateEnums) {
        this.seckillId = seckillId;
        this.state = seckillStateEnums.getState();
        this.stateInfo = seckillStateEnums.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillSuccess=" + seckillSuccess +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillSuccess getSeckillSuccess() {
        return seckillSuccess;
    }

    public void setSeckillSuccess(SeckillSuccess seckillSuccess) {
        this.seckillSuccess = seckillSuccess;
    }
}
