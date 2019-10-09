package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.seckill.entity.SeckillSuccess;

public interface SeckillSuccessDao {

    /**
     * 插入秒杀成功的记录
     * @param seckillId
     * @param userPhone
     * @return 返回值>1表示插入的行数；
     *         返回0表示插入失败
     */
    int insertSeckillSuccess(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);


    /**
     * 根据id查询并返回秒杀实体对象
     * @param seckillId
     * @return
     */
    SeckillSuccess queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
