package com.lhz.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.lhz.commonutil.Res;
import com.lhz.servicebase.ComException;
import com.lhz.vodservice.Utils.ConstantVodUtils;
import com.lhz.vodservice.Utils.InitVodCilent;
import com.lhz.vodservice.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: lhz
 * @Description: 视频服务前端控制器
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * @author: lhz
     * @Description: 上传视频
     */
    @PostMapping("uploadAlyiVideo")
    public Res uploadAlyiVideo(MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        return Res.ok().data("videoId",videoId);
    }

    /**
     * @author: lhz
     * @Description: 删除视频
     */
    @DeleteMapping("removeAlyVideo/{id}")
    public Res removeAlyVideo(@PathVariable String id) {
        try {

            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(id);

            client.getAcsResponse(request);
            return Res.ok();
        }catch(Exception e) {
            e.printStackTrace();
            throw new ComException(303,"删除视频失败");
        }
    }

    /**
     * @author: lhz
     * @Description: 删除多个视频
     */
    @DeleteMapping("delete-batch")
    public Res deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 根据视频id获取视频凭证
     */
    @GetMapping("getPlayAuth/{id}")
    public Res getPlayAuth(@PathVariable String id) {
        try {

            DefaultAcsClient client =
                    InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

            request.setVideoId(id);

            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Res.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new ComException(303,"获取凭证失败");
        }
    }
}
