package com.lhz.vodservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.lhz.servicebase.ComException;
import com.lhz.vodservice.Utils.ConstantVodUtils;
import com.lhz.vodservice.Utils.InitVodCilent;
import com.lhz.vodservice.service.VodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
/**
 * @author: lhz
 * @Description: 视频服务实现类
 */
@Service
public class VodServiceImpl implements VodService {

    /**
     * @author: lhz
     * @Description: 上传视频
     */
    @Override
    public String uploadVideoAly(MultipartFile file) {

        try {

            String fileName = file.getOriginalFilename();

            String title = fileName.substring(0, fileName.lastIndexOf("."));

            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @author: lhz
     * @Description: 删除视频
     */
    @Override
    public void removeMoreAlyVideo(List videoIdList) {
        try {
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            DeleteVideoRequest request = new DeleteVideoRequest();

            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new ComException(303,"删除视频失败");
        }
    }
}
