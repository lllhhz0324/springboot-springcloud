package com.lhz.eduservice.controller;

import com.lhz.commonutil.Res;
import com.lhz.eduservice.entity.EduChapter;
import com.lhz.eduservice.entity.chapter.ChapterVo;
import com.lhz.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lhz
 * @Description: 课程前端控制器
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程大纲
     */
    @GetMapping("getChapterVideo/{courseId}")
    public Res getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getSyllabusByCourseId(courseId);
        return Res.ok().data("allChapterVideo",list);
    }

    /**
     * @author: lhz
     * @Description: 添加章节
     */
    @PostMapping("addChapter")
    public Res addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 查询章节
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public Res getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return Res.ok().data("chapter",eduChapter);
    }

    /**
     * @author: lhz
     * @Description: 修改章节
     */
    @PostMapping("updateChapter")
    public Res updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 删除章节
     */
    @DeleteMapping("{chapterId}")
    public Res deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapterByChapterId(chapterId);
        if(flag) {
            return Res.ok();
        } else {
            return Res.error();
        }

    }
}

