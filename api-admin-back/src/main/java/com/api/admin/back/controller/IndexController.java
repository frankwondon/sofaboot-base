package com.api.admin.back.controller;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @RequestMapping("login")
    public String login(){
        return "login";
    }


    @RequestMapping("dologin")
    public String dologin(@RequestParam String account, @RequestParam String password ){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        subject.login(token);
        return "/index";
    }
}
