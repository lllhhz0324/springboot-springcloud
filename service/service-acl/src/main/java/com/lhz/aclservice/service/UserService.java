package com.lhz.aclservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.aclservice.entity.User;

/**
 * @author: lhz
 * @Description: 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * @author: lhz
     * @Description: 获取用户信息
     */
    User selectByUsername(String username);
}
