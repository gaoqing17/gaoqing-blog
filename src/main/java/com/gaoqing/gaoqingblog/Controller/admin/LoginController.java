package com.gaoqing.gaoqingblog.Controller.admin;


import com.gaoqing.gaoqingblog.service.admin.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //进入登录页面
    @GetMapping
    public String LoginPage(){
        return "admin/login";
    }

    //    登录验证
    @RequestMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String Password,
                        Model attributes){
        //获得当前的subject
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据，封装为token加密
        UsernamePasswordToken token = new UsernamePasswordToken(userName, Password);
        try {
            subject.login(token);
            return "admin/index";
        } catch (UnknownAccountException e) {
            attributes.addAttribute("message", "用户名不存在");
            return "admin/login";
        } catch (IncorrectCredentialsException e) {
            attributes.addAttribute("message", "密码错误");
            return "admin/login";
        }
    }

//注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/admin";
    }
}
