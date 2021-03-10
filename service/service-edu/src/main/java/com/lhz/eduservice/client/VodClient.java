package com.lhz.eduservice.client;

import com.lhz.commonutil.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * @author: lhz
 * @Description: 调用视频微服务
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * @author: lhz
     * @Description: 根据视频id删除视频
     */
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public Res removeAlyVideo(@PathVariable("id") String id);

    /**
     * @author: lhz
     * @Description: 删除多个视频
     */
    @DeleteMapping("/eduvod/video/delete-batch")
    public Res deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
