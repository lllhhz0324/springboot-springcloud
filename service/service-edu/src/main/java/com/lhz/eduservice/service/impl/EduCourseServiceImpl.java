package com.lhz.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.eduservice.entity.EduCourse;
import com.lhz.eduservice.entity.EduCourseDescription;
import com.lhz.eduservice.entity.frontvo.CourseFrontVo;
import com.lhz.eduservice.entity.frontvo.CourseWebVo;
import com.lhz.eduservice.entity.vo.CourseInfoVo;
import com.lhz.eduservice.entity.vo.CoursePublishVo;
import com.lhz.eduservice.mapper.EduCourseMapper;
import com.lhz.eduservice.service.EduChapterService;
import com.lhz.eduservice.service.EduCourseDescriptionService;
import com.lhz.eduservice.service.EduCourseService;
import com.lhz.eduservice.service.EduPeriodService;
import com.lhz.servicebase.ComException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 课程服务实现类
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduPeriodService periodService;

    @Autowired
    private EduChapterService chapterService;

    /**
     * @author: lhz
     * @Description: 添加课程基本信息
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0) {
            throw new ComException(303,"添加课程信息失败");
        }

        String cid = eduCourse.getId();

        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程基本信息
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    /**
     * @author: lhz
     * @Description: 修改课程信息
     */
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new ComException(303,"修改课程信息失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程信息
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * @author: lhz
     * @Description: 删除课程
     */
    @Override
    public void removeCourse(String courseId) {

        periodService.deletePeriodByCourseId(courseId);

        chapterService.deleteChapterByCourseId(courseId);

        courseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);
        if(result == 0) { //失败返回
            throw new ComException(303,"删除失败");
        }
    }

    /**
     * @author: lhz
     * @Description: 条件查询课程带分页
     */
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 条件查询
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam,wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程信息
     */
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

}
