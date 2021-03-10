package com.lhz.vipservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhz.vipservice.entity.UcenterMember;

/**
 * @author: lhz
 * @Description: 会员 Mapper 接口
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
