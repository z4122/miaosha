package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})//spring-service 依赖dao实现类
public class SeckillServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSecKillList() {
        List<Seckill> seckillList = seckillService.getSecKillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void executeSeckill() {
        SeckillExecution seckillExecution = seckillService.executeSeckill(1000,17617080625L,"0c67cf2fd0f5c4bbac865e477bb0ef6c");
        logger.info("seckillExecution={}",seckillExecution);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer =  seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }
}