package com.api.app.controller.auth;

import com.api.app.util.RequestUtil;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.service.UserService;
import com.module.common.Response;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wangdong
 * @date: 2019/6/24 10:20
 */
@RestController
@RequestMapping("auth")
@Api(tags = "鉴权")
public class AuthController {
    @Resource
    private UserService userService;

    @GetMapping("visitorLogin")
    @ApiOperation("游客登陆")
    @ApiImplicitParam(name = "x-token",dataType = "String",paramType="header")
    public Response<String> visitor(@RequestHeader("deviceId") String deviceId, @RequestHeader(name = "user-agent") String  userAgent, HttpServletRequest request){
        return Response.success(userService.getToken(AppTokenDto.builder()
                .ip(RequestUtil.getIpAddress(request))
                .userAgent(userAgent)
                .subject(deviceId)
                .appUserType(AppUserType.USER_VISITOR)
                .build()));
    }

    @PostMapping("userLogin")
    @ApiOperation("用户登陆")
    @ApiImplicitParam(dataType = "String",paramType="header",name = "x-token")
    public Response<String> login(@RequestHeader("deviceId") String deviceId,@RequestHeader(name = "user-agent")String  userAgent ,@RequestBody LoginQuery query,HttpServletRequest request){
        userService.login(query);
        return Response.success(userService.getToken(AppTokenDto.builder()
                .ip(RequestUtil.getIpAddress(request))
                .userAgent(userAgent)
                .subject(deviceId)
                .mobile(query.getMobile())
                .appUserType(AppUserType.USER)
                .build()));
    }

    @PostMapping("loginOut")
    @ApiOperation("用户登出(不包含游客)")
    public Response loginVerifyCode(){
        return Response.success(true);
    }

}
