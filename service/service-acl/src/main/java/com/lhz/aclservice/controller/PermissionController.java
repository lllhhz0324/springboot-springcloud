package com.lhz.aclservice.controller;



import com.lhz.aclservice.entity.Permission;
import com.lhz.aclservice.service.PermissionService;
import com.lhz.commonutil.Res;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author: lhz
 * @Description: 权限菜单前端控制器
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * @author: lhz
     * @Description: 获取全部菜单
     */
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Res indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return Res.ok().data("children",list);
    }

    /**
     * @author: lhz
     * @Description: 递归删除菜单
     */
    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return Res.ok();
    }
    /**
     * @author: lhz
     * @Description: 给角色分配权限
     */
    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Res doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShip(roleId,permissionId);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 根据角色获取菜单
     */
    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Res toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Res.ok().data("children", list);
    }

    /**
     * @author: lhz
     * @Description: 新增菜单
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Res save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 修改菜单
     */
    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Res updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Res.ok();
    }

}

