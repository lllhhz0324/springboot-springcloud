package com.lhz.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: lhz
 * @Description: 章节实体类
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
