package com.lhz.aclservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.aclservice.entity.MyUserRole;
import com.lhz.aclservice.mapper.UserRoleMapper;
import com.lhz.aclservice.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author: lhz
 * @Description: 用户服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, MyUserRole> implements UserRoleService {

}
