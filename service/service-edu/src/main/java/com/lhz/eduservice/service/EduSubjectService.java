package com.lhz.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.eduservice.entity.EduSubject;
import com.lhz.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: lhz
 * @Description: 课程科目服务接口
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * @author: lhz
     * @Description: 添加课程分类
     */
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    /**
     * @author: lhz
     * @Description: 课程分类展示
     */
    List<OneSubject> getAllOneTwoSubject();
}
