package com.lhz.eduservice.controller;

import com.lhz.commonutil.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @Description: 登陆前端控制器
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public Res login() {
        return Res.ok().data("token","admin");
    }

    @GetMapping("info")
    public Res info() {
        return Res.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
