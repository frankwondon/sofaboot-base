package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.dto.AppProductDto;
import com.module.admin.app.query.AppProductQuery;
import com.module.admin.app.result.AppProductResult;
import com.module.admin.app.service.AppProductService;
import com.module.common.Response;
import com.module.common.bean.AdminCurrentUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "产品管理")
@RestController
@RequestMapping("/appadmin/product/index")
public class AppProductController {
    @SofaReference
    private AppProductService appProductService;


    @GetMapping("list")
    @ApiOperation(value = "商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status"  ,paramType = "query",required = true,value = "0上架1下架2售完3即将卖完"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询条件"),
    })
    public Response<IPage<AppProductResult>> productList(AppProductQuery query){
        return Response.success(appProductService.list(query));
    }

    @GetMapping("preCreate")
    @ApiOperation(value = "预创建商品",notes = "返回一个订单编号,单个订单编号防止重复提交")
    public Response<String> preProduct(){
        return Response.success(appProductService.productNum());
    }

    @PostMapping("create")
    @ApiOperation(value = "创建商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "商品名称"),
            @ApiImplicitParam(name = "num"  ,paramType = "query",required = true,value = "商品编号自动生成"),
            @ApiImplicitParam(name = "ShelfType" ,paramType = "query" ,required = true,value = "上架类型0立即上架1下架状态2自动上架"),
            @ApiImplicitParam(name = "autoShelfTimeStr" ,paramType = "query" ,required = true,value = "自动上架时间"),
            @ApiImplicitParam(name = "mainImg" ,paramType = "query" ,required = true,value = "首页展示图片"),
            @ApiImplicitParam(name = "productType" ,paramType = "query" ,required = true,value = "商品类型ID"),
            @ApiImplicitParam(name = "descImg" ,paramType = "query" ,required = true,value = "产品详情图"),
            @ApiImplicitParam(name = "expressTemplateId" ,paramType = "query" ,required = true,value = "运费模板"),
            @ApiImplicitParam(name = "shipType" ,paramType = "query" ,required = true,value = "发货方式0物流1无需物流"),
            @ApiImplicitParam(name = "payType" ,paramType = "query" ,required = true,value = "支付方式0在线支付"),
            @ApiImplicitParam(name = "skus" ,paramType = "query" ,required = true,value = "定义的skus格式[{name:'克重',values:[{key:'0.5g',check:true}]}]"),
    })
    public Response<Boolean> createProduct(AppProductDto dto, @ApiIgnore AdminCurrentUser user){
        appProductService.createProduct(dto,user.getId());
        return Response.success(true);
    }


    @GetMapping("updateDetail")
    @ApiOperation(value = "获取更新信息")
    public Response<AppProductDto> updateDetail(Integer productId){
        return Response.success( appProductService.findUpdateDetail(productId));
    }

    @PostMapping("update")
    @ApiOperation(value = "编辑商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name"  ,paramType = "query",required = true,value = "商品名称"),
            @ApiImplicitParam(name = "num"  ,paramType = "query",required = true,value = "商品编号自动生成"),
            @ApiImplicitParam(name = "ShelfType" ,paramType = "query" ,required = true,value = "0立即上架1下架"),
            @ApiImplicitParam(name = "productGram" ,paramType = "query" ,required = true,value = "产品净重"),
            @ApiImplicitParam(name = "mainImg" ,paramType = "query" ,required = true,value = "首页展示图片多张,隔开"),
            @ApiImplicitParam(name = "productType" ,paramType = "query" ,required = true,value = "商品类型ID"),
            @ApiImplicitParam(name = "descImg" ,paramType = "query" ,required = true,value = "产品详情图"),
            @ApiImplicitParam(name = "expressTemplateId" ,paramType = "query" ,required = true,value = "运费模板"),
            @ApiImplicitParam(name = "shipType" ,paramType = "query" ,required = true,value = "发货方式0物流1无需物流"),
            @ApiImplicitParam(name = "payType" ,paramType = "query" ,required = true,value = "支付方式0在线支付"),
            @ApiImplicitParam(name = "skus" ,paramType = "query" ,required = true,value = "定义的skus格式[{name:'克重',values:[{key:'0.5g',check:true}]}]"),
            @ApiImplicitParam(name = "finalSkus" ,paramType = "query" ,required = true,value = "生成的格式类似[sku的结果]")
     })
    public Response<Boolean> updateProduct(AppProductDto dto,@ApiIgnore AdminCurrentUser user){
        appProductService.update(dto);
        return Response.success(true);
    }


    @PostMapping("shelfAndObtained")
    @ApiOperation(value = "上下架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId"  ,paramType = "query",required = true,value = "商品ID"),
            @ApiImplicitParam(name = "status"  ,paramType = "query",required = true,value = "0上架1下架"),
    })
    public Response<Boolean> ShelfAndObtained(Integer productId,Integer status){
        appProductService.shelfAndObtained(productId,status);
        return Response.success(true);
    }

    @GetMapping("productCountDown")
    @ApiOperation(value = "售卖完/即将售卖完的接口")
    public Response<Integer> productCountDown(){
        return Response.success(appProductService.productCountDown());
    }
}
