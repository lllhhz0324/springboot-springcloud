package com.lhz.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.aclservice.entity.Permission;


import java.util.List;

/**
 * @author: lhz
 * @Description: 权限服务接口
 */
public interface PermissionService extends IService<Permission> {

    /**
     * @author: lhz
     * @Description: 获取全部菜单
     */
    List<Permission> findAllMenu();

    /**
     * @author: lhz
     * @Description: 根据角色id获取菜单
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * @author: lhz
     * @Description: 给角色分配权限
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    /**
     * @author: lhz
     * @Description: 递归删除菜单
     */
    void removeChildById(String id);

    /**
     * @author: lhz
     * @Description: 根据用户id获取用户菜单
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * @author: lhz
     * @Description: 根据用户id获取权限
     */
    List<JSONObject> selectPermissionByUserId(String id);

    /**
     * @author: lhz
     * @Description: 获取全部菜单
     */
    List<Permission> queryAllMenu();

    /**
     * @author: lhz
     * @Description: 删除子节点
     */
    void deleteChildById(String id);

    /**
     * @author: lhz
     * @Description: 分配权限给角色
     */
    void addRolePermissionRealtionShip(String roleId, String[] permissionId);
}
