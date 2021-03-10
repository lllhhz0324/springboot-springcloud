package com.lhz.staservice.client;

import com.lhz.commonutil.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-vip")
public interface UcenterClient {

    /**
     * @author: lhz
     * @Description: 查询当天注册人数
     */
    @GetMapping("/educenter/member/countRegister/{day}")
    public Res countRegister(@PathVariable("day") String day);
}
