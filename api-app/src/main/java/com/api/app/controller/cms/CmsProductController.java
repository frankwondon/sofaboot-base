package com.api.app.controller.cms;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductTypeResult;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "产品界面")
@RestController
@RequestMapping("/cms/product")
public class CmsProductController {
    @SofaReference
    private CmsProductTypeService cmsProductTypeService;
    @SofaReference
    private CmsProductService productService;

    @ApiOperation(value = "获取产品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",paramType = "query" ,required = true,value = "产品ID"),
    })
    @GetMapping("/productDetail")
    public Response<CmsProduct> list(Integer id) {
        return Response.success( productService.findById(id));
    }

    @ApiOperation(value = "商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",paramType = "query" ,required = true,value = "产品ID"),
            @ApiImplicitParam(name = "limit",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "page",paramType = "query" ,required = true,value = "当前页数"),
    })
    @GetMapping("/productList")
    public Response<IPage<CmsProduct>> list(ProductQuery query) {
        return Response.success( productService.showList(query));
    }

    @ApiOperation(value = "商品类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit",paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "page",paramType = "query" ,required = true,value = "当前页数"),
    })
    @GetMapping("/productTypeList")
    public Response< List<CmsProductTypeResult>> productTypeList(ProductQuery query) {
        List<CmsProductTypeResult> cmsProductTypeResults = cmsProductTypeService.showList(query);
        return Response.success(cmsProductTypeResults);
    }


}
