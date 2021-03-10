package com.lhz.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * @author: lhz
 * @Description: 视频服务接口
 */
public interface VodService {
    /**
     * @author: lhz
     * @Description: 上传视频
     */
    String uploadVideoAly(MultipartFile file);

    /**
     * @author: lhz
     * @Description: 删除视频
     */
    void removeMoreAlyVideo(List videoIdList);
}
