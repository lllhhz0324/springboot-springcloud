package com.lhz.aclservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.aclservice.entity.MyUserRole;
import com.lhz.aclservice.entity.Role;
import com.lhz.aclservice.mapper.RoleMapper;
import com.lhz.aclservice.service.RoleService;
import com.lhz.aclservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lhz
 * @Description: 角色服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;


    /**
     * @author: lhz
     * @Description: 根据用户获取角色信息
     */
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {

        List<Role> allRolesList =baseMapper.selectList(null);

        List<MyUserRole> existUserRoleList = userRoleService.list(new QueryWrapper<MyUserRole>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        List<Role> assignRoles = new ArrayList<Role>();
        for (Role role : allRolesList) {
            if(existRoleList.contains(role.getId())) {
                assignRoles.add(role);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoles);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    /**
     * @author: lhz
     * @Description: 根据用户分配角色
     */
    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<MyUserRole>().eq("user_id", userId));

        List<MyUserRole> userRoleList = new ArrayList<>();
        for(String roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            MyUserRole userRole = new MyUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);

            userRoleList.add(userRole);
        }
        userRoleService.saveBatch(userRoleList);
    }
    /**
     * @author: lhz
     * @Description: 根据用户id获取角色信息
     */
    @Override
    public List<Role> selectRoleByUserId(String id) {
        List<MyUserRole> userRoleList = userRoleService.list(new QueryWrapper<MyUserRole>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<Role> roleList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleList;
    }
}
