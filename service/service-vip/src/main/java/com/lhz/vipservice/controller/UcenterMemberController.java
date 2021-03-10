package com.lhz.vipservice.controller;



import com.lhz.commonutil.JwtUtils;
import com.lhz.commonutil.Res;
import com.lhz.commonutil.ordervo.UcenterMemberOrder;
import com.lhz.vipservice.entity.UcenterMember;
import com.lhz.vipservice.entity.vo.RegisterVo;
import com.lhz.vipservice.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: lhz
 * @Description: 会员前端控制器
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    /**
     * @author: lhz
     * @Description: 会员登录
     */
    @PostMapping("login")
    public Res loginUser(@RequestBody UcenterMember member) {

        String token = memberService.login(member);
        return Res.ok().data("token",token);
    }

    /**
     * @author: lhz
     * @Description: 会员注册
     */
    @PostMapping("register")
    public Res registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Res.ok();
    }

    /**
     * @author: lhz
     * @Description: 根据token获取会员信息
     */
    @GetMapping("getMemberInfo")
    public Res getMemberInfo(HttpServletRequest request) {

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return Res.ok().data("userInfo",member);
    }

    /**
     * @author: lhz
     * @Description: 根据用户id获取用户信息
     */
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * @author: lhz
     * @Description: 查询注册人数
     */
    @GetMapping("countRegister/{day}")
    public Res countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return Res.ok().data("countRegister",count);
    }
}

