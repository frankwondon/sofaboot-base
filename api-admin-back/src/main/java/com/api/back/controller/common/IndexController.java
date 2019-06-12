package com.api.back.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.result.BackUserResult;
import com.module.admin.back.service.BackMenuService;
import com.module.admin.back.service.BackRoleService;
import com.module.admin.back.service.BackUserService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.bean.CurrentUser;
import com.module.common.util.ShiroPasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "三大后台公共接口")
@Controller
public class IndexController {
    @SofaReference
    private BackMenuService backMenuService;
    @SofaReference
    private BackUserService backUserService;
    @SofaReference
    private BackRoleService backRoleService;


    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",paramType = "query" ,required = true,value = "账号"),
            @ApiImplicitParam(name = "password",paramType = "query" ,required = true,value = "密码")
    })
    public Response<Boolean> dologin(@RequestParam String account, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        BackUserResult userResult = backUserService.getByAccount(account);
        if (userResult == null) {
            return Response.fail(ResponseCode.C_302);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(account, ShiroPasswordUtil.encpwd(password,userResult.getSalt()));
        subject.login(token);
        return Response.success(buildUserInfo(userResult));
    }

    private JSONObject buildUserInfo(BackUserResult byAccount){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",byAccount.getUsername());
        jsonObject.put("roleName",byAccount.getRoleName());
        return jsonObject;
    }

    @GetMapping("/loginOut")
    @ResponseBody
    public  Response<Boolean> loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Response.success(true);
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
