package org.seckill.enums;

public enum SeckillStateEnums {
    SUCCESS(1,"秒杀成功"),
    END(2,"活动结束"),
    REPEATE_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"内部错误");


    private int state;
    private String stateInfo;

    SeckillStateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnums stateOf(int index){
        for(SeckillStateEnums seckillStateEnums:values()){
            if(seckillStateEnums.getState()==index){
                return seckillStateEnums;
            }
        }
        return null;
    }
}
