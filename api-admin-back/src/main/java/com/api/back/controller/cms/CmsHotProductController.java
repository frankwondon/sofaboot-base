package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.result.CmsProductResult;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.Response;
import com.module.common.bean.AdminCurrentUser;
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

@Api(tags = "重点产品管理")
@RestController
@RequestMapping("/cmsadmin/main/hotProduct")
public class CmsHotProductController {

    @SofaReference
    private CmsProductService productService;

    @SofaReference
    private CmsProductTypeService productTypeService;

    @ApiOperation("查询产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "page" ,paramType = "query",required = true,value = "当前页"),
    })
    @GetMapping("/list")
    public Response<IPage<CmsProductResult>> list(PageQuery pageQuery){
        return Response.success(productService.listHot(pageQuery));
    }


    @ApiOperation(value = "新增或修改产品",notes = "有ID就是修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId" ,paramType = "query",value = "有ID就是修改"),
            @ApiImplicitParam(name = "name",paramType = "query" ,required = true,value = "产品名称"),
            @ApiImplicitParam(name = "imgUrl" ,paramType = "query",required = true,value = "图片地址"),
            @ApiImplicitParam(name = "videoUrl",paramType = "query" ,required = true,value = "视频地址"),
            @ApiImplicitParam(name = "descTitle",paramType = "query" ,required = true,value = "标题"),
            @ApiImplicitParam(name = "descDesc",paramType = "query" ,required = true,value = "描述"),
            @ApiImplicitParam(name = "descText",paramType = "query" ,required = true,value = "详细描述"),
            @ApiImplicitParam(name = "sort" ,paramType = "query",required = true,value = "顺序"),
            @ApiImplicitParam(name = "locked",paramType = "query" ,value = "0启用,1禁用 默认禁用"),
    })
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(@ApiIgnore CmsProduct productType,@ApiIgnore AdminCurrentUser currentUser){
        productType.setShowType(1);
        productType.setTypeId(-1);
        productService.addOrUpdate(productType,currentUser);
        return Response.success(true);
    }


    @ApiOperation("删除产品")
    @PostMapping("del")
    public Response del(Integer id){
        productService.del(id);
        return Response.success(true);
    }


    @ApiOperation("启用/禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId"  ,paramType = "query",required = true,value = "要操作的ID"),
            @ApiImplicitParam(name = "disable"  ,paramType = "query",required = true,value = "true启用false禁用"),
    })
    @PostMapping("disable")
    public Response disable(Integer id, Boolean disable,@ApiIgnore AdminCurrentUser currentUser){
        productService.disable(id,disable,currentUser);
        return Response.success(true);
    }
}
