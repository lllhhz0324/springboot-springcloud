package com.lhz.eduservice.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.frontvo.CourseFrontVo;
import com.lhz.eduservice.entity.frontvo.CourseWebVo;
import com.lhz.eduservice.entity.vo.CourseInfoVo;
import com.lhz.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 课程服务接口
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * @author: lhz
     * @Description: 添加课程基本信息
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程基本信息
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * @author: lhz
     * @Description: 修改课程信息
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程信息
     */
    CoursePublishVo publishCourseInfo(String id);

    /**
     * @author: lhz
     * @Description: 删除课程
     */
    void removeCourse(String courseId);

    /**
     * @author: lhz
     * @Description: 条件查询带分页查询课程信息
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
