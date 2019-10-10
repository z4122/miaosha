package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SeckillSuccessDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SeckillSuccess;
import org.seckill.enums.SeckillStateEnums;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SeckillSuccessDao seckillSuccessDao;

    private String slat = "qwer!@$@!FW125adsf!(*()Nb";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Seckill> getSecKillList() {
        return seckillDao.queryAll(0,100);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill == null){
            return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime())
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());

        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    /**
     * 使用注解控制方法
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        try {
            if(md5.equals(getMd5(seckillId))==false||md5==null){
                throw new SeckillException("校验失败");
            }

            Date nowTime = new Date();

            int updateCount = seckillDao.reduceNumber(seckillId,nowTime);

            if(updateCount<=0){
                throw new SeckillCloseException("不在活动时间");
            }
            else{
                int insertNumber = seckillSuccessDao.insertSeckillSuccess(seckillId,userPhone);
                if(insertNumber<=0){
                    throw new RepeatKillException("重复插入");
                }
                else{
                    SeckillSuccess seckillSuccess = seckillSuccessDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecution(seckillId,SeckillStateEnums.SUCCESS,seckillSuccess);
                }
            }

        } catch (RepeatKillException e1){
            throw new RepeatKillException("重复插入");
        } catch (SeckillCloseException e2){
            throw new SeckillCloseException("已经关闭");
        } catch(Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }
}
