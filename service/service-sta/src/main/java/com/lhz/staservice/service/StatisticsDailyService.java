package com.lhz.staservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.staservice.entity.StatisticsDaily;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 统计服务接口
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * @author: lhz
     * @Description: 统计注册人数
     */
    void registerCount(String day);

    /**
     * @author: lhz
     * @Description: 图表显示
     */
    Map<String, Object> getShowData(String type, String begin, String end);
}
