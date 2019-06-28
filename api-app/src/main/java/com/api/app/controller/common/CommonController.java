package com.api.app.controller.common;

import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "公共接口")
@RestController
@RequestMapping("common")
public class CommonController {
    @Value("${server.imgserver}")
    private String imgServer;

    @GetMapping("imgServer")
    @ApiOperation("图片服务器地址")
    public Response<String> getBaseUrl(){
        return Response.success(imgServer);
    }

}
