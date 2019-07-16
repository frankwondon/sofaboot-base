package com.api.cms.controller.common;

import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "公共接口")
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${server.imgserver}")
    private String fileServer;


    @ApiOperation("获取文件服务器地址")
    public Response<String> getFileServer(){
        return Response.success(fileServer);
    }
}
