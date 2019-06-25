package com.api.app.controller.auth;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.base.common.service.SMSSendService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdong
 * @date: 2019/6/25 10:55
 */
@RestController
@RequestMapping("verifyCode")
@Api(tags = "验证码接口")
public class VerifyCodeController {
    @SofaReference
    private SMSSendService smsSendService;

    @PostMapping("loginVerifyCode")
    @ApiOperation("获取登陆验证码")
    public Response loginVerifyCode(@RequestBody String mobile){
        smsSendService.sendLoginVerifyCode(mobile);
        return Response.success(true);
    }
}
