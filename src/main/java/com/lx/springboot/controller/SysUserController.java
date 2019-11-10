package com.lx.springboot.controller;


import com.lx.springboot.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@Controller
public class SysUserController {

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping({"", "/user/login"})
    public String login(){
        return "login";
    }

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    @PostMapping("/user/doLogin")
    public String doLogin(SysUser user, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        SecurityUtils.getSubject().login(token);
        //用户名保存到session中
        session.setAttribute("username", user.getUsername());
        return "redirect:/index";
    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/user/login";
    }
}
