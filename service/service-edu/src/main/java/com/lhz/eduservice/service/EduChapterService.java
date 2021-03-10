package com.lhz.eduservice.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.eduservice.entity.EduChapter;
import com.lhz.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * @author: lhz
 * @Description: 章节服务接口
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程大纲
     */
    List<ChapterVo> getSyllabusByCourseId(String courseId);

    /**
     * @author: lhz
     * @Description: 根据章节id删除章节
     */
    boolean deleteChapterByChapterId(String chapterId);

    /**
     * @author: lhz
     * @Description: 根据课程id删除章节
     */
    void deleteChapterByCourseId(String courseId);
}
