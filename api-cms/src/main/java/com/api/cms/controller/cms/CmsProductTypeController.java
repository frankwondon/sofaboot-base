package com.api.cms.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.entity.CmsProductType;
import com.module.admin.cms.service.CmsProductTypeService;
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

@Api(tags = "产品类型管理")
@RestController
@RequestMapping("/cmsadmin/productType")
public class CmsProductTypeController {
    @SofaReference
    private CmsProductTypeService productTypeService;

    @ApiOperation("查询产品类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit" ,required = true,value = "每页数量"),
            @ApiImplicitParam(name = "page" ,required = true,value = "当前页"),
    })
    @GetMapping("/list")
    public Response<IPage<CmsProductType>> list(PageQuery pageQuery){
        return Response.success(productTypeService.list(pageQuery));
    }

    @ApiOperation(value = "新增或修改产品类型",notes = "有ID就是修改,新增默认为禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",type = "query",required = true,value = "id主键"),
            @ApiImplicitParam(name = "name",type = "query" ,required = true,value = "名称"),
            @ApiImplicitParam(name = "sort" ,type = "query",required = true,value = "排序:数字顺序")
    })
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(@ApiIgnore  CmsProductType productType,@ApiIgnore CurrentUser currentUser){
        productTypeService.addOrUpdate(productType,currentUser);
        return Response.success(true);
    }


    @ApiOperation("删除产品类型")
    @PostMapping("del")
    public Response del(Integer id){
        productTypeService.del(id);
        return Response.success(true);
    }
    @ApiOperation("禁用产品类型")
    @PostMapping("disable")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,value ="产品ID" ),
            @ApiImplicitParam(name = "disable",required = true,value ="0启用 1禁用" ),
    })
    public Response disable(Integer id,Boolean disable, @ApiIgnore CurrentUser currentUser){
        productTypeService.disable(id,disable,currentUser);
        return Response.success(true);
    }



}
