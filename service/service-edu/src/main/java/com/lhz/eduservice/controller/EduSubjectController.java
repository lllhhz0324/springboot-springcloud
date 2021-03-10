package com.lhz.eduservice.controller;

import com.lhz.commonutil.Res;
import com.lhz.eduservice.entity.subject.OneSubject;
import com.lhz.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author: lhz
 * @Description: 课程科目前端控制器
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    /**
     * @author: lhz
     * @Description: 添加课程分类
     */
    @PostMapping("addSubject")
    public Res addSubject(MultipartFile file) {
        subjectService.saveSubject(file,subjectService);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 课程分类树形列表
     */
    @GetMapping("getAllSubject")
    public Res getAllSubject() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Res.ok().data("list",list);
    }

}

