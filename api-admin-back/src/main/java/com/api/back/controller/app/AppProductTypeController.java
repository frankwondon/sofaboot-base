package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.entity.AppProductType;
import com.module.admin.app.service.AppProductTypeService;
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
@Api(tags = "产品类型管理")
@RestController
@RequestMapping("/appadmin/product/type")
public class AppProductTypeController {
    @SofaReference
    private AppProductTypeService productTypeService;

    @GetMapping("list")
    @ApiOperation(value = "商品类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询条件"),
    })
    public Response<IPage<AppProduct>> list(PageQuery query){
        return Response.success(productTypeService.list(query));
    }

    @PostMapping("addOrUpdate")
    @ApiOperation(value = "新增/修改",notes = "有ID就是修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "类型名称"),
            @ApiImplicitParam(name = "desc"  ,paramType = "query",required = true,value = "类型描述"),
            @ApiImplicitParam(name = "sort"  ,paramType = "query",required = true,value = "排序"),
    })
    public Response<Boolean> list(AppProductType productType){
        productTypeService.addOrUpdateProductType(productType);
        return Response.success(true);
    }

    @PostMapping("disable")
    @ApiOperation(value = "禁用/启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id"  ,paramType = "query",required = true,value = "商品类型ID"),
            @ApiImplicitParam(name = "disable" ,paramType = "query" ,required = true,value = "true启用/false禁用"),
    })
    public Response<Boolean> disable(Integer id,Boolean disable){
        productTypeService.disable(id,disable?0:1);
        return Response.success(true);
    }
}
