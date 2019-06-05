package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.service.CmsBannerService;
import com.module.common.Response;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Banner管理")
@RestController
@RequestMapping("/cmsadmin/main/banner")
public class CmsBannerController {
    @SofaReference
    private CmsBannerService cmsBannerService;


    @ApiOperation("查询Banner列表")
    @GetMapping("/list")
    public Response<IPage<CmsBanner>> list(PageQuery pageQuery){
        return Response.success( cmsBannerService.list(pageQuery));
    }
    @ApiOperation(value = "新增或修改Banner",notes = "有ID就是修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = false,value = "有ID就是修改"),
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "名称"),
            @ApiImplicitParam(name = "link"  ,paramType = "query",required = true,value = "跳转"),
            @ApiImplicitParam(name = "imgUrl" ,paramType = "query" ,required = true,value = "图片地址"),
            @ApiImplicitParam(name = "belong" ,paramType = "query" ,required = true,value = "所属分组0官网首页"),
            @ApiImplicitParam(name = "descTitle" ,paramType = "query" ,required = true,value = "标题"),
            @ApiImplicitParam(name = "descText" ,paramType = "query" ,required = true,value = "详细描述"),
            @ApiImplicitParam(name = "sort"  ,paramType = "query",required = true,value = "顺序"),
    })
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(@ApiIgnore CmsBanner cmsBanner,@ApiIgnore CurrentUser currentUser){
        cmsBannerService.addOrUpdate(cmsBanner,currentUser);
        return Response.success(true);
    }

    @ApiOperation("删除Banner")
    @PostMapping("del")
    public Response del(Integer id){
        cmsBannerService.del(id);
        return Response.success(true);
    }
    @ApiOperation("启用/禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "要操作的ID"),
            @ApiImplicitParam(name = "disable"  ,paramType = "query",required = true,value = "true启用false禁用"),
    })
    @PostMapping("disable")
    public Response disable(Integer id, Boolean disable,@ApiIgnore CurrentUser currentUser){
        cmsBannerService.disable(id,disable,currentUser);
        return Response.success(true);
    }
}
