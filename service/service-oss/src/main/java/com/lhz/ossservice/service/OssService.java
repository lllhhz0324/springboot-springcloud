package com.lhz.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lhz
 * @Description: oss服务接口
 */
public interface OssService {
    /**
     * @author: lhz
     * @Description: 上传头像到oss
     */
    String uploadFileAvatar(MultipartFile file);
}
