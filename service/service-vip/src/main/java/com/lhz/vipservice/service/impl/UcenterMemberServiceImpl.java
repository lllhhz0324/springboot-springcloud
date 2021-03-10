package com.lhz.vipservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhz.commonutil.JwtUtils;
import com.lhz.servicebase.ComException;
import com.lhz.vipservice.entity.UcenterMember;
import com.lhz.vipservice.entity.vo.RegisterVo;
import com.lhz.vipservice.mapper.UcenterMemberMapper;
import com.lhz.vipservice.service.UcenterMemberService;
import com.lhz.vipservice.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: lhz
 * @Description: 会员服务实现类
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @author: lhz
     * @Description: 会员登录方法
     */
    @Override
    public String login(UcenterMember member) {

        String mobile = member.getMobile();
        String password = member.getPassword();

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new ComException(303,"登录失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if(mobileMember == null) {
            throw new ComException(303,"登录失败");
        }

        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new ComException(303,"登录失败");
        }

        if(mobileMember.getIsDisabled()) {
            throw new ComException(303,"登录失败");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    /**
     * @author: lhz
     * @Description: 会员注册
     */
    @Override
    public void register(RegisterVo registerVo) {

        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new ComException(303,"注册失败");
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)) {
            throw new ComException(303,"注册失败");
        }

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new ComException(303,"注册失败");
        }

        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        baseMapper.insert(member);
    }

    /**
     * @author: lhz
     * @Description: 根据id判断会员身份
     */
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    /**
     * @author: lhz
     * @Description: 查询注册人数
     */
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
