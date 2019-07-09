package com.api.app.controller.product;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.query.ShopProductQuery;
import com.module.api.app.result.AppBannerListResult;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.service.ShopProductService;
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
@Api(tags = "商城排序列表接口")
public class ShopProductController {


    @SofaReference
    private ShopProductService shopProductService;

    @ApiOperation(value = "默认排序  0默认初始加载 1价格升2价格降 3销量降4销量降")
    @GetMapping("/showSortList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortId"  ,paramType = "query",required = true,value = "0默认初始加载 1价格升2价格降 3销量降4销量升"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数")
    })
    public Response<List<AppBannerListResult>> showSortList(ShopProductQuery query){
        return Response.success(shopProductService.showSortList(query));
    }

}
