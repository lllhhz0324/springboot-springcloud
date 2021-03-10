package com.lhz.eduservice.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.eduservice.entity.EduTeacher;

import java.util.Map;

/**
 * @author: lhz
 * @Description: 讲师服务接口
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * @author: lhz
     * @Description: 分页查询讲师
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
