package com.lhz.eduservice.controller;

import com.lhz.commonutil.Res;
import com.lhz.eduservice.client.VodClient;
import com.lhz.eduservice.entity.EduPeriod;
import com.lhz.eduservice.service.EduPeriodService;
import com.lhz.servicebase.ComException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lhz
 * @Description: 课时前端控制器
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduPeriodService periodService;

    @Autowired
    private VodClient vodClient;

    /**
     * @author: lhz
     * @Description: 添加小节
     */
    @PostMapping("addVideo")
    public Res addVideo(@RequestBody EduPeriod eduPeriod) {
        periodService.save(eduPeriod);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 删除课时包括视频
     */
    @DeleteMapping("{id}")
    public Res deleteVideo(@PathVariable String id) {

        EduPeriod eduPeriod = periodService.getById(id);
        String videoSourceId = eduPeriod.getVideoSourceId();

        if(!StringUtils.isEmpty(videoSourceId)) {

            Res result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 303) {
                throw new ComException(303,"删除视频失败");
            }
        }
        periodService.removeById(id);
        return Res.ok();
    }
}

