package com.api.back.controller.common;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackMenuService;
import com.module.admin.back.service.BackUserService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.bean.CurrentUser;
import com.module.common.util.ShiroPasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ApiIgnore
@Controller
public class IndexController {
    @SofaReference
    private BackMenuService backMenuService;
    @SofaReference
    private BackUserService backUserService;

    @PostMapping("/login")
    @ResponseBody
    public Response<Boolean> dologin(@RequestParam String account, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        BackUser byAccount = backUserService.getByAccount(account);
        if (byAccount == null) {
            return Response.fail(ResponseCode.C_302);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, ShiroPasswordUtil.encpwd(password,byAccount.getSalt()));
        subject.login(token);
        return Response.success(true);
    }

    @GetMapping("/loginOut")
    public String loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/html/login.html";
    }

    @GetMapping("/index")
    public String toIndex(String site, HttpServletRequest request, CurrentUser user) {
        List<BackMenu> backMenus = backMenuService.loadMenu(user, site);
        request.setAttribute("site", site);
        request.setAttribute("menus", backMenus);
        request.setAttribute("username", user.getUsername());
        return "index";
    }

}
