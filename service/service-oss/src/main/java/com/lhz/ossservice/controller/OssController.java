package com.lhz.ossservice.controller;


import com.lhz.commonutil.Res;
import com.lhz.ossservice.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lhz
 * @Description: oss服务前端控制器
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * @author: lhz
     * @Description: 上传头像到oss
     */
    @PostMapping
    public Res uploadOssFile(MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return Res.ok().data("url",url);
    }

}
