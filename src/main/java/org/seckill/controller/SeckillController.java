package org.seckill.controller;


import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnums;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getSecKillList();
        model.addAttribute("list",seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);

        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);

        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
                    method = RequestMethod.POST,
                    produces = "{application/json;charset=UTF-8}")//防止中文乱码问题
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,"没有开放url");
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution")
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long phone ){
        if(phone==null)
            return new SeckillResult<SeckillExecution>(false,"手机号为空");

        SeckillResult<SeckillExecution> seckillResult;
        try{
            SeckillExecution seckillExecution =  seckillService.executeSeckill(seckillId,phone,md5);
            seckillResult = new SeckillResult<SeckillExecution>(true,seckillExecution);
            return seckillResult;
        }catch (RepeatKillException e1){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnums.REPEATE_KILL);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }catch (SeckillCloseException e2){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnums.END);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }catch (Exception e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillStateEnums.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false, seckillExecution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Date> time(){
        Date date = new Date();
        SeckillResult<Date> result = new SeckillResult<Date>(true,date);
        return result;
    }

}
