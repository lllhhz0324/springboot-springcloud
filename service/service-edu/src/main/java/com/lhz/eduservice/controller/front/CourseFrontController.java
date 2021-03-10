package com.lhz.eduservice.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhz.commonutil.JwtUtils;
import com.lhz.commonutil.Res;
import com.lhz.commonutil.ordervo.CourseWebVoOrder;
import com.lhz.eduservice.client.OrdersClient;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.chapter.ChapterVo;
import com.lhz.eduservice.entity.frontvo.CourseFrontVo;
import com.lhz.eduservice.entity.frontvo.CourseWebVo;
import com.lhz.eduservice.service.EduChapterService;
import com.lhz.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    /**
     * @author: lhz
     * @Description: 条件查询课程带分页
     */
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public Res getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                  @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        return Res.ok().data(map);
    }

    /**
     * @author: lhz
     * @Description: 查看课程详情
     */
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Res getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {

        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getSyllabusByCourseId(courseId);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Res.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程信息
     */
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }
}












