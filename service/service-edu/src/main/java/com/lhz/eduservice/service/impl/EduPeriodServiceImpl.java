package com.lhz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.eduservice.client.VodClient;
import com.lhz.eduservice.entity.EduPeriod;
import com.lhz.eduservice.mapper.EduPeriodMapper;
import com.lhz.eduservice.service.EduPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @Description: 课时服务实现类
 */
@Service
public class EduPeriodServiceImpl extends ServiceImpl<EduPeriodMapper, EduPeriod> implements EduPeriodService {

    @Autowired
    private VodClient vodClient;

    /**
     * @author: lhz
     * @Description: 根据id删除课时
     */
    @Override
    public void deletePeriodByCourseId(String courseId) {

        QueryWrapper<EduPeriod> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduPeriod> eduVideoList = baseMapper.selectList(wrapperVideo);

        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduPeriod eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        }

        /**
         * @author: lhz
         * @Description: 根据id删除多个视频
         */
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduPeriod> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
