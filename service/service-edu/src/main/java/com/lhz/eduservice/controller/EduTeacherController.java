package com.lhz.eduservice.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhz.commonutil.Res;
import com.lhz.eduservice.entity.EduTeacher;
import com.lhz.eduservice.entity.vo.TeacherQuery;
import com.lhz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lhz
 * @Description: 讲师前端控制器
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    /**
     * @author: lhz
     * @Description: 查询讲师所有数据
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Res findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Res.ok().data("items",list);
    }

    /**
     * @author: lhz
     * @Description: 逻辑删除讲师
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Res removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return Res.ok();
        } else {
            return Res.error();
        }
    }

    /**
     * @author: lhz
     * @Description: 分页查询讲师
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    public Res pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();

        return Res.ok().data("total",total).data("rows",records);
    }

    /**
     * @author: lhz
     * @Description: 条件查询带分页
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Res pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        wrapper.orderByDesc("gmt_create");
        teacherService.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return Res.ok().data("total",total).data("rows",records);
    }

    /**
     * @author: lhz
     * @Description: 添加讲师
     */
    @PostMapping("addTeacher")
    public Res addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return Res.ok();
        } else {
            return Res.error();
        }
    }

    /**
     * @author: lhz
     * @Description: 根据id查询讲师
     */
    @GetMapping("getTeacher/{id}")
    public Res getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return Res.ok().data("teacher",eduTeacher);
    }

    /**
     * @author: lhz
     * @Description: 修改讲师信息
     */
    @PostMapping("updateTeacher")
    public Res updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return Res.ok();
        } else {
            return Res.error();
        }
    }
}

