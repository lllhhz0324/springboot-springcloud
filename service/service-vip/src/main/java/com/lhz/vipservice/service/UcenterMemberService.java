package com.lhz.vipservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhz.vipservice.entity.UcenterMember;
import com.lhz.vipservice.entity.vo.RegisterVo;

/**
 * @author: lhz
 * @Description: 会员接口
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * @author: lhz
     * @Description: 会员登录
     */
    String login(UcenterMember member);

    /**
     * @author: lhz
     * @Description: 会员注册
     */
    void register(RegisterVo registerVo);

    /**
     * @author: lhz
     * @Description: 根据id判断会员身份
     */
    UcenterMember getOpenIdMember(String openid);

    /**
     * @author: lhz
     * @Description: 查看注册人数
     */
    Integer countRegisterDay(String day);
}
