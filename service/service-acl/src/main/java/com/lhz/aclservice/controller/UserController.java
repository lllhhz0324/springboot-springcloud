package com.lhz.aclservice.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhz.aclservice.entity.User;
import com.lhz.aclservice.service.RoleService;
import com.lhz.aclservice.service.UserService;
import com.lhz.commonutil.MD5;
import com.lhz.commonutil.Res;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: lhz
 * @Description: 用户前端控制器
 */
@RestController
@RequestMapping("/admin/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * @author: lhz
     * @Description: 获取管理用户分页列表
     */
    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Res index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }

        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return Res.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    /**
     * @author: lhz
     * @Description: 新增管理用户
     */
    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public Res save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return Res.ok();
    }
    /**
     * @author: lhz
     * @Description: 修改管理用户
     */
    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public Res updateById(@RequestBody User user) {
        userService.updateById(user);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 删除管理用户
     */
    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        userService.removeById(id);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 根据id列表删除管理用户
     */
    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Res batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Res.ok();
    }
    /**
     * @author: lhz
     * @Description: 根据用户获取角色信息
     */
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Res toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Res.ok().data(roleMap);
    }

    /**
     * @author: lhz
     * @Description: 根据用户分配角色
     */
    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Res doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return Res.ok();
    }
}

