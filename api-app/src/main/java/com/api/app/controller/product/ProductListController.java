package com.api.app.controller.product;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.query.ProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import com.module.api.app.service.ProductService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/25 14:00
 */
@RestController
@RequestMapping("product")
@Api(tags = "产品列表接口")
public class ProductListController {
    @SofaReference
    private ProductService productService;
    @GetMapping("listType")
    @ApiOperation("产品类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit",required = true,value = "默认请求第一个选中类型的数据第一页数据条数")
    })
    public Response<List<AppProductTypeResult>> listType(@RequestParam(defaultValue ="6") Integer limit){
       return Response.success(productService.listType(limit));
    }
    @ApiOperation("产品列表")
    @GetMapping("listProduct")
    public Response<List<AppProductResult>> listProduct(ProductQuery query){
        return Response.success(productService.listProduct(query)) ;
    }
}
