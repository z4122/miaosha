package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SeckillSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillSuccessDaoTest {

    @Autowired
    SeckillSuccessDao seckillSuccessDao;

    @Test
    public void insertSeckillSuccess() {
        int insertCount = seckillSuccessDao.insertSeckillSuccess(1000,17717080625L);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        SeckillSuccess seckillSuccess = seckillSuccessDao.queryByIdWithSeckill(1000,17717080625L);

        System.out.println(seckillSuccess);
    }
}