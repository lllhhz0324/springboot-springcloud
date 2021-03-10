package com.lhz.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhz.commonutil.Res;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.EduTeacher;
import com.lhz.eduservice.service.EduCourseService;
import com.lhz.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    /**
     * @author: lhz
     * @Description: 分页查询讲师
     */
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public Res getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        return Res.ok().data(map);
    }

    /**
     * @author: lhz
     * @Description: 讲师详情功能
     */
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Res getTeacherFrontInfo(@PathVariable String teacherId) {

        EduTeacher eduTeacher = teacherService.getById(teacherId);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return Res.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}












