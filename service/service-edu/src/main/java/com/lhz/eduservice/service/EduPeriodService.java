package com.lhz.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.eduservice.entity.EduPeriod;

/**
 * @author: lhz
 * @Description: 课时服务接口
 */
public interface EduPeriodService extends IService<EduPeriod> {

    /**
     * @author: lhz
     * @Description: 根据课程id删除课时
     */
    void deletePeriodByCourseId(String courseId);
}
