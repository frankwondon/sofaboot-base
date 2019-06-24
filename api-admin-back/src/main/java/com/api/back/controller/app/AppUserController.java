package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.result.UserManagerResult;
import com.module.admin.app.service.AppUserService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/appadmin/user/index")
public class AppUserController {
    @SofaReference
    private AppUserService appUserService;

    @GetMapping("list")
    @ApiOperation("用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询条件"),
    })
    public Response<IPage<UserManagerResult>> list(PageQuery pageQuery) {
        return Response.success(appUserService.listOfUser(pageQuery));
    }

    @GetMapping("disable")
    @ApiOperation("启用/禁用用户")
    public Response<Boolean> disable(Integer uesrId, Boolean disable) {
        appUserService.disable(uesrId, disable ? 0 : 1);
        return Response.success(true);
    }
}
