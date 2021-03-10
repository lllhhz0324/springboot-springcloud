package com.lhz.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.eduservice.entity.EduChapter;
import com.lhz.eduservice.entity.EduPeriod;
import com.lhz.eduservice.entity.chapter.ChapterVo;
import com.lhz.eduservice.entity.chapter.VideoVo;
import com.lhz.eduservice.mapper.EduChapterMapper;
import com.lhz.eduservice.service.EduChapterService;
import com.lhz.eduservice.service.EduPeriodService;
import com.lhz.servicebase.ComException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @Description: 课程服务实现类
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduPeriodService periodService;

    /**
     * @author: lhz
     * @Description: 根据课程id查询课程大纲
     */
    @Override
    public List<ChapterVo> getSyllabusByCourseId(String courseId) {

        // 获得章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        // 获得课时
        QueryWrapper<EduPeriod> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduPeriod> eduVideoList = periodService.list(wrapperVideo);

        List<ChapterVo> finalList = new ArrayList<>();

        // 封装章节
        for (int i = 0; i < eduChapterList.size(); i++) {

            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            // 封装课时
            List<VideoVo> videoList = new ArrayList<>();

            for (int m = 0; m < eduVideoList.size(); m++) {

                EduPeriod eduVideo = eduVideoList.get(m);
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

    /**
     * @author: lhz
     * @Description: 根据章节id删除章节，删完小节才能删章节的策略
     */
    @Override
    public boolean deleteChapterByChapterId(String chapterId) {

        QueryWrapper<EduPeriod> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);

        int count = periodService.count(wrapper);
        if(count >0) {
            throw new ComException(303,"先删除小节");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }

    /**
     * @author: lhz
     * @Description: 根据课程id删除章节
     */
    @Override
    public void deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
