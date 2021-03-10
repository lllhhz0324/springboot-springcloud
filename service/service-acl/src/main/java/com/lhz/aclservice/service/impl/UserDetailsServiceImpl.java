package com.lhz.aclservice.service.impl;



import com.lhz.aclservice.service.PermissionService;
import com.lhz.aclservice.service.UserService;
import com.lhz.springsecurity.entity.SecurityUser;
import com.lhz.springsecurity.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: lhz
 * @Description: 用户详情实现类
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * @author: lhz
     * @Description: 根据账号获取用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.lhz.aclservice.entity.User user = userService.selectByUsername(username);


        User curUser = new User();
        BeanUtils.copyProperties(user,curUser);

        List<String> authorities = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
