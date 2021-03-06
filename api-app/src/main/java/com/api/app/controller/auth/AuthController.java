package com.api.app.controller.auth;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthLogin;
import com.api.app.util.RequestUtil;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.result.LoginResult;
import com.module.api.app.service.UserService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.AppTokenDto;
import com.module.common.constant.AppUserType;
import com.module.common.constant.HeaderConstant;
import com.module.common.util.AppTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangdong
 * @date: 2019/6/24 10:20
 */
@RestController
@RequestMapping("auth")
@Api(tags = "鉴权")
public class AuthController {

    @SofaReference
    private UserService userService;

    @GetMapping("visitorLogin")
    @ApiOperation("游客登陆")
    @ApiImplicitParam(name = "x-token",dataType = "String",paramType="header")
    public Response<String> visitor(@RequestHeader(name=HeaderConstant.DEVICEID_NAME) String deviceId,
                                    @RequestHeader(name = HeaderConstant.USERAGENT_NAME)String  userAgent
                                    , HttpServletRequest request){
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
    public Response<LoginResult> login(@RequestHeader(name=HeaderConstant.DEVICEID_NAME) String deviceId,
                                       @RequestHeader(name = HeaderConstant.USERAGENT_NAME)String  userAgent ,
                                       @RequestBody LoginQuery query, HttpServletRequest request){
        LoginResult login = userService.login(query);
        AppCurrentUser user=new AppCurrentUser();
        user.setId(login.getUserId());
        user.setCellPhoneNum(login.getCellPhoneNum());
        user.setUsername(login.getUsername());
        login.setToken(userService.getToken(AppTokenDto.builder()
                .ip(RequestUtil.getIpAddress(request))
                .userAgent(userAgent)
                .subject(deviceId)
                .mobile(query.getMobile())
                .appUserType(AppUserType.USER)
                .sessionUser(user)
                .build()));
        return Response.success(login);
    }

    @PostMapping("loginOut")
    @ApiOperation("用户登出(不包含游客)")
    @AuthLogin
    public Response<Boolean> loginOut(@ApiIgnore AppCurrentUser user,@RequestHeader(name = HeaderConstant.TOKEN_NAME) String token){
        AppTokenDto decode = AppTokenUtil.decode(token);
        userService.loginOut(decode);
        return Response.success(true);
    }



}
