package com.lhz.servicecms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhz.commonutil.Res;
import com.lhz.servicecms.entity.CrmBanner;
import com.lhz.servicecms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lhz
 * @Description: 后台banner前端控制器
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    /**
     * @author: lhz
     * @Description: 分页查询banner
     */
    @GetMapping("pageBanner/{page}/{limit}")
    public Res pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return Res.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    /**
     * @author: lhz
     * @Description: 添加banner
     */
    @PostMapping("addBanner")
    public Res addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Res.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Res get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Res.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Res updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Res.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Res.ok();
    }
}

