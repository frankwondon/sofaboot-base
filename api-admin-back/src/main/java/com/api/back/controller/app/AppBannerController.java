package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "Banner管理")
@RestController
@RequestMapping("/appadmin/main/banner")
public class AppBannerController {

    @GetMapping
    @ApiOperation("Banner列表")
    public Response list(){
        return null;
    }
}
