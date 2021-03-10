package com.lhz.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.lhz.aclservice.service.IndexService;
import com.lhz.commonutil.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 页面前端控制器
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * @author: lhz
     * @Description: 根据token获取用户信息
     */
    @GetMapping("info")
    public Res info(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Res.ok().data(userInfo);
    }

    /**
     * @author: lhz
     * @Description: 获取菜单
     */
    @GetMapping("menu")
    public Res getMenu(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Res.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public Res logout(){
        return Res.ok();
    }

}
