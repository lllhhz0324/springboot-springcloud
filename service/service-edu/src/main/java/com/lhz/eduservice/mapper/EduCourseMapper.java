package com.lhz.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.frontvo.CourseWebVo;
import com.lhz.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * @author: lhz
 * @Description: 课程 Mapper 接口
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * @author: lhz
     * @Description: 根据课程id获取课程确认信息
     */
    CoursePublishVo getPublishCourseInfo(String courseId);

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程信息
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
