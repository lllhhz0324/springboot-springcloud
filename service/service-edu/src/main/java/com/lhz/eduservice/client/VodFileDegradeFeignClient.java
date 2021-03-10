package com.lhz.eduservice.client;


import com.lhz.commonutil.Res;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lhz
 * @Description: 视频出错
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Res removeAlyVideo(String id) {
        return Res.error().message("删除视频出错了");
    }

    @Override
    public Res deleteBatch(List<String> videoIdList) {
        return Res.error().message("删除多个视频出错了");
    }
}
