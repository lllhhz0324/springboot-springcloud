package com.lhz.aclservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.aclservice.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 角色服务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * @author: lhz
     * @Description: 根据用户获取角色信息
     */
    Map<String, Object> findRoleByUserId(String userId);

    /**
     * @author: lhz
     * @Description: 根据用户分配角色
     */
    void saveUserRoleRealtionShip(String userId, String[] roleId);


    List<Role> selectRoleByUserId(String id);
}
