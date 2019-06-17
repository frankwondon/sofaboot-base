package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.query.AppProductQuery;
import com.module.admin.app.service.AppProductService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "产品管理")
@RestController
@RequestMapping("/appadmin/product/index")
public class AppProductController {
    @SofaReference
    private AppProductService appProductService;


    @RequestMapping("list")
    @ApiOperation(value = "商品列表",notes = "有ID就是修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status"  ,paramType = "query",required = true,value = "0上架1下架2售完3即将卖完"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "跳转"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "图片地址"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询"),
    })
    public Response<IPage<AppProduct>> productList(AppProductQuery query){
        return Response.success(appProductService.list(query));
    }

    @RequestMapping("create")
    @ApiOperation(value = "创建商品")
    public Response createProduct(){

        return null;
    }

    @ApiOperation(value = "上下架")
    @RequestMapping("shelfAndObtained")
    public Response ShelfAndObtained(){
        return null;
    }
}
