package com.lhz.eduservice.controller;



import com.lhz.commonutil.Res;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.vo.CourseInfoVo;
import com.lhz.eduservice.entity.vo.CoursePublishVo;
import com.lhz.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lhz
 * @Description: 课程前端控制器
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
     * @author: lhz
     * @Description: 课程列表
     */
    @GetMapping
    public Res getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return Res.ok().data("list",list);
    }

    /**
     * @author: lhz
     * @Description: 添加课程基本信息
     */
    @PostMapping("addCourseInfo")
    public Res addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return Res.ok().data("courseId",id);
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程基本信息
     */
    @GetMapping("getCourseInfo/{courseId}")
    public Res getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Res.ok().data("courseInfoVo",courseInfoVo);
    }

    /**
     * @author: lhz
     * @Description: 修改课程信息
     */
    @PostMapping("updateCourseInfo")
    public Res updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询确认信息
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public Res getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Res.ok().data("publishCourse",coursePublishVo);
    }

    /**
     * @author: lhz
     * @Description: 课程发布
     */
    @PostMapping("publishCourse/{id}")
    public Res publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 删除课程
     */
    @DeleteMapping("{courseId}")
    public Res deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Res.ok();
    }

}

