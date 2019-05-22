package com.api.admin.back.controller;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.service.BackMenuService;
import com.module.common.Response;
import com.module.common.bean.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @SofaReference
    private BackMenuService backMenuService;

    @PostMapping("/login")
    @ResponseBody
    public Response<Boolean> dologin(@RequestParam String account, @RequestParam String password ){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        subject.login(token);
        return Response.Builder.success(true);
    }

    @PostMapping("/loginOut")
    public String loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/html/login.html";
    }

    @GetMapping("/index")
    public String toIndex(String site, HttpServletRequest request, CurrentUser user){
        List<BackMenu> backMenus = backMenuService.loadMenu(user,site);
        request.setAttribute("site",site);
        request.setAttribute("menus",backMenus);
        return "index";
    }

}
