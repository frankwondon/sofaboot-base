package com.api.app.controller.product;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.query.ShopProductQuery;
import com.module.api.app.result.BannerListResult;
import com.module.api.app.service.BannerListService;
import com.module.api.app.service.ShopProductService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ShopProductController
 * @Description 商城页面
 * @Author YJT
 * @Date 2019/7/2 0002 13:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("shopProduct")
@Api(tags = "商城排序列表接口 开发中")
public class ShopProductController {



    //参数 2. 点击加载   请求参数 默认的0或1
    //参数 3. 点击加载   请求参数 价格0或1
    //参数 4. 点击加载   请求参数 销量0或1
    @SofaReference
    private ShopProductService shopProductService;

    @ApiOperation(value = "默认排序  完成")
    @PostMapping("/showPretermit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortId"  ,paramType = "query",required = true,value = "默认0----点击加载0，1"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数")
    })
    public Response<List<BannerListResult>> bannerShopList(ShopProductQuery query){
        return Response.success(shopProductService.showPretermit(query));
    }
    @ApiOperation(value = "价格排序  开发")
    @PostMapping("/showPrice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortId"  ,paramType = "query",required = true,value = "价格-  点击加载，1"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数")
    })
    public Response<List<BannerListResult>> showPrice(ShopProductQuery query){
        return Response.success(shopProductService.showPrice(query));
    }
}
