package com.api.back.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.result.BackUserResult;
import com.module.admin.back.service.BackMenuService;
import com.module.admin.back.service.BackRoleService;
import com.module.admin.back.service.BackUserService;
import com.module.common.Response;
import com.module.common.ResponseCode;
import com.module.common.util.ShiroPasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Api(tags = "三大后台公共接口")
@RestController
public class IndexController {
    @SofaReference
    private BackMenuService backMenuService;
    @SofaReference
    private BackUserService backUserService;
    @SofaReference
    private BackRoleService backRoleService;
    @Value("${server.imgserver}")
    private String fileServer;

    @PostMapping("/login")
    @ApiOperation("登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",paramType = "query" ,required = true,value = "账号"),
            @ApiImplicitParam(name = "password",paramType = "query" ,required = true,value = "密码")
    })
    public Response<JSONObject> dologin(@RequestParam String account, @RequestParam String password) {
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
        jsonObject.put("userType",byAccount.getUserType());
        jsonObject.put("fileServer",fileServer);
        return jsonObject;
    }

    @GetMapping("/loginOut")
    public  Response<Boolean> loginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Response.success(true);
    }


}
