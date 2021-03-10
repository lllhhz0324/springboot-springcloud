package com.lhz.aclservice.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 页面前端控制器
 */
public interface IndexService {

    /**
     * @author: lhz
     * @Description: 根据用户名获取用户登录信息
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * @author: lhz
     * @Description: 根据用户名获取动态菜单
     */
    List<JSONObject> getMenu(String username);

}
