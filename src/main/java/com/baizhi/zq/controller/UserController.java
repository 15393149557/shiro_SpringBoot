package com.baizhi.zq.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            //认证  获得主题对象
            Subject subject = SecurityUtils.getSubject();

            //封装token=username+password
            AuthenticationToken token=new UsernamePasswordToken(username,password);

            //认证
            subject.login(token);

            return "redirect:/main/main.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("未知的账号异常==账号不存在");
            return "redirect:/test/login.jsp";
        }catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常==密码错误");
            return "redirect:/test/login.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(){

        //认证  获得主体对象
        Subject subject = SecurityUtils.getSubject();
        //退出
        subject.logout();
        return "redirect:/test/login.jsp";
    }
}
