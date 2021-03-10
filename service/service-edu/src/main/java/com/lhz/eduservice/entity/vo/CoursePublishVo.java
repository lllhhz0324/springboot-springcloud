package com.lhz.eduservice.entity.vo;

import lombok.Data;

/**
 * @author: lhz
 * @Description: 课程确认信息实体类
 */
@Data
public class CoursePublishVo {

    private String id;

    private String title;

    private String cover;

    private Integer lessonNum;

    private String subjectLevelOne;

    private String subjectLevelTwo;

    private String teacherName;

    private String price;
}
