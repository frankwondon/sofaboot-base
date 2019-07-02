package com.api.app.controller.product;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.query.ProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import com.module.api.app.result.BannerListResult;
import com.module.api.app.service.ProductService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import com.sun.org.apache.bcel.internal.classfile.Code;
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

    @ApiOperation(value = "搜索",notes = "搜索查询商品关键字")
    @GetMapping("searchKeyWord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord",paramType = "query" ,required = true,value = "每页条数"),
    })
    public Response<AppProductResult> searchKeyWord(String keyWord){
        List<AppProductResult> appProductResultList = productService.searchKeyWord(keyWord);
        if (appProductResultList!=null&&appProductResultList.size()>0) {
            return Response.success(productService.searchKeyWord(keyWord));
        }
        return Response.success("暂无商品");
    }

    @ApiOperation(value = "商品id", notes = "点击商品图片，价格，名字查看商品详情")
    @GetMapping("/getProductById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",paramType = "query",required = true,value = "商品id")
    })
    public Response<List<BannerListResult>> getProductById(Integer productId){
        //todo
        return Response.success(productService.getProductById(productId));
    }



    @ApiOperation("精品小件")
    @GetMapping("competitiveList")
    public Response<List<AppProductResult>> competitiveList( ){
        return Response.success(productService.competitiveList()) ;
    }
    @ApiOperation("奢华享受")
    @GetMapping("luxuriousList")
    public Response<List<AppProductResult>> luxuriousList( ){
        return Response.success(productService.luxuriousList()) ;
    }

    @ApiOperation("随便看看  todo 分页")
    @GetMapping("casualList")
    public Response<List<AppProductResult>> casualList( ){
        return Response.success(productService.casualList()) ;
    }





}
