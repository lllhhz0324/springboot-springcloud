package com.lhz.staservice.controller;

import com.lhz.commonutil.Res;
import com.lhz.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 统计日数据
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    /**
     * @author: lhz
     * @Description: 统计注册人数
     */
    @PostMapping("registerCount/{day}")
    public Res registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 图标显示
     */
    @GetMapping("showData/{type}/{begin}/{end}")
    public Res showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = staService.getShowData(type,begin,end);
        return Res.ok().data(map);
    }
}

