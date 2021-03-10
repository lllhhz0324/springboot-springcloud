package com.lhz.aclservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhz.aclservice.entity.Permission;

import java.util.List;

/**
 * @author: lhz
 * @Description: 权限 Mapper 接口
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}
