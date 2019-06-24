package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.service.AppBannerService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "Banner管理")
@RestController
@RequestMapping("/appadmin/main/banner")
public class AppBannerController {
    @SofaReference
    private AppBannerService bannerService;

    @GetMapping("list")
    @ApiOperation("Banner列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询条件"),
    })
    public Response list(PageQuery pageQuery){
        return Response.success(bannerService.list(pageQuery));
    }
    @PostMapping("add")
    @ApiOperation("新增Banner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "名称"),
            @ApiImplicitParam(name = "link"  ,paramType = "query",required = true,value = "跳转链接"),
            @ApiImplicitParam(name = "imgUrl"  ,paramType = "query",required = true,value = "图片路径"),
            @ApiImplicitParam(name = "belong"  ,paramType = "query",required = true,value = "0首页 1商城"),
            @ApiImplicitParam(name = "productId"  ,paramType = "query",required = true,value = "关联的商品Id"),
            @ApiImplicitParam(name = "type"  ,paramType = "query",required = true,value = "0商品banner1其他普通直接跳转H5"),
            @ApiImplicitParam(name = "sort"  ,paramType = "query",required = true,value = "排序"),
    })
    public Response add(AppBanner banner){
        bannerService.add(banner);
        return Response.success(true);
    }



    @PostMapping("update")
    @ApiOperation("修改Banner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "ID"),
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "名称"),
            @ApiImplicitParam(name = "link"  ,paramType = "query",required = true,value = "跳转链接"),
            @ApiImplicitParam(name = "imgUrl"  ,paramType = "query",required = true,value = "图片路径"),
            @ApiImplicitParam(name = "belong"  ,paramType = "query",required = true,value = "0首页 1商城"),
            @ApiImplicitParam(name = "productId"  ,paramType = "query",required = true,value = "关联的商品Id"),
            @ApiImplicitParam(name = "type"  ,paramType = "query",required = true,value = "0商品banner1其他普通直接跳转H5"),
            @ApiImplicitParam(name = "sort"  ,paramType = "query",required = true,value = "排序"),
    })
    public Response update(AppBanner banner){
        bannerService.add(banner);
        return Response.success(true);
    }

    @PostMapping("del")
    @ApiOperation("删除Banner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "ID"),
    })
    public Response del(Integer id){
        bannerService.del(id);
        return Response.success(true);
    }


    @PostMapping("disable")
    @ApiOperation("启用/禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "ID"),
            @ApiImplicitParam(name = "disable"  ,paramType = "query",required = true,value = "启用true/禁用false"),
    })
    public Response disable(Integer id, Boolean disable){
        bannerService.disable(id,disable?0:1);
        return Response.success(true);
    }
}
