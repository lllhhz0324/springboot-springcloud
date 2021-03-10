package com.lhz.staservice.schedule;


import com.lhz.staservice.service.StatisticsDailyService;
import com.lhz.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;



    /**
     * @author: lhz
     * @Description: 每天凌晨三点统计数据
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void task2() {
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
