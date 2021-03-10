package com.lhz.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lhz
 * @Description: 课程一级分类实体类
 */
@Data
public class OneSubject {
    private String id;

    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}
