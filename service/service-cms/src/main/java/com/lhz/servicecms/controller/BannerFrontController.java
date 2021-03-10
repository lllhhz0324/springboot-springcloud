package com.lhz.servicecms.controller;


import com.lhz.commonutil.Res;
import com.lhz.servicecms.entity.CrmBanner;
import com.lhz.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author: lhz
 * @Description: banner显示前端控制器
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    /**
     * @author: lhz
     * @Description: 查询所有banner
     */
    @GetMapping("getAllBanner")
    public Res getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return Res.ok().data("list",list);
    }
}

