package org.seckill.service;


import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 *
 */
public interface SeckillService {

    /**
     * 获取所有的秒杀商品信息
     * @return
     */
    List<Seckill> getSecKillList();

    Seckill getById(long seckillId);

    /**
     * 时间到则暴露秒杀地址
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);


    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;
}
