package com.api.app.controller.product;


import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.result.BannerListResult;
import com.module.api.app.service.BannerListService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author yjt
 * @date: 2019/7/1 14:39
 */
@RestController
@RequestMapping("banner")
@Api(tags = "产品列表接口")
public class BannerListController {
    @SofaReference
    private BannerListService bannerService;

    @ApiOperation(value = "首页轮播图", notes = "5张")
    @GetMapping("/bannerIndexList")
    public Response<List<BannerListResult>> bannerList(){
        return Response.success(bannerService.bannerIndexList());
    }

    @ApiOperation(value = "商城轮播图", notes = "5张")
    @GetMapping("/bannerShopList")
    public Response<List<BannerListResult>> bannerShopList(){
        return Response.success(bannerService.bannerShopList());
    }


}
