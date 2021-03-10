package com.lhz.eduservicemsm.controller;


import com.lhz.commonutil.Res;
import com.lhz.eduservicemsm.service.MsmService;
import com.lhz.eduservicemsm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: lhz
 * @Description: 信息服务前端控制器
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @author: lhz
     * @Description: 发送注册验证码
     */
    @GetMapping("send/{phone}")
    public Res sendMsm(@PathVariable String phoneNum) {

        String code = redisTemplate.opsForValue().get(phoneNum);
        if(!StringUtils.isEmpty(code)) {
            return Res.ok();
        }

        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);

        boolean isSend = msmService.send(param,phoneNum);
        if(isSend) {
            redisTemplate.opsForValue().set(phoneNum,code,5, TimeUnit.MINUTES);
            return Res.ok();
        } else {
            return Res.error().message("短信发送失败");
        }
    }
}
