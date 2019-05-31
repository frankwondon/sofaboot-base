package com.api.back.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.entity.CmsProductType;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.Response;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(description = "产品管理")
@RestController
@RequestMapping("/cmsadmin/product")
public class CmsProductController {

    @SofaReference
    private CmsProductService productService;

    @SofaReference
    private CmsProductTypeService productTypeService;

    @ApiOperation("查询产品列表")
    @GetMapping("/list")
    public Response<IPage<CmsProduct>> list(PageQuery pageQuery){
        return Response.success(productService.list(pageQuery));
    }



    @ApiOperation("查询产品类型下拉")
    @GetMapping("/listType")
    public Response<List<CmsProductType>> listType(){
        return Response.success(productTypeService.listSelect());
    }

    @ApiOperation(value = "新增或修改产品",notes = "有ID就是修改")
    @PostMapping("addOrUpdate")
    public Response addOrUpdate(CmsProduct productType,@ApiIgnore CurrentUser currentUser){
        productService.addOrUpdate(productType,currentUser);
        return Response.success(true);
    }


    @ApiOperation("删除产品")
    @PostMapping("del")
    public Response del(Integer id){
        productService.del(id);
        return Response.success(true);
    }
    @ApiOperation("禁用产品")
    @PostMapping("disable")
    public Response disable(Integer id, @ApiIgnore CurrentUser currentUser){
        productService.disable(id,currentUser);
        return Response.success(true);
    }
}
