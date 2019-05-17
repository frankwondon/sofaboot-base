package com.api.admin.back.controller.auth;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backadmin/auth")
public class LoginController {



    @RequestMapping("/test1")
    public String tes1(){
        boolean permitted = SecurityUtils.getSubject().isPermitted("1:*");
        SecurityUtils.getSubject().checkPermission("1:2:3");
        SecurityUtils.getSubject().checkPermission("1:2:8");
        return "index";
    }

    @RequestMapping("/test2")
    public String tes2(){
        boolean permitted =SecurityUtils.getSubject().isPermitted("1:2:3");
        boolean permitted1=SecurityUtils.getSubject().isPermitted("1:2:4");
        return "index";
    }


    @RequestMapping("/test3")
    public String tes3(){
        boolean permitted =SecurityUtils.getSubject().isPermitted("1:2:5");
        return "index";
    }

    @RequestMapping("/test4")
    public String tes4(){
        boolean permitted =SecurityUtils.getSubject().isPermitted("1:*:3");
        return "index";
    }

    public String tes5(){
        boolean permitted =SecurityUtils.getSubject().isPermitted("1:*:6");
        return "index";
    }

}
