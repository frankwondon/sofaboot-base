package com.api.app.controller.product;


import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppBannerListResult;
import com.module.api.app.service.BannerListService;
import com.module.api.app.service.ProductService;
import com.module.common.Response;
import io.swagger.annotations.Api;
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

    @SofaReference
    private ProductService productService;


    @ApiOperation(value = "首页数据", notes = "bannerList轮播图5张competitiveList精美小件luxuriousList奢华产品")
    @GetMapping("/bannerIndexList")
    public Response<JSONObject> bannerList(){
        //轮播图
        List<AppBannerListResult> bannerList = bannerService.bannerIndexList();
        //精品小件
        List<AppProductResult> competitiveList = bannerService.competitiveList();
        //奢华产品
        List<AppProductResult> luxuriousList = bannerService.luxuriousList();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("bannerList",bannerList);
        jsonObject.put("competitiveList",competitiveList);
        jsonObject.put("luxuriousList",luxuriousList);
        return Response.success(jsonObject);
    }



    @ApiOperation(value = "商城轮播图", notes = "5张")
    @GetMapping("/bannerShopList")
    public Response<List<AppBannerListResult>> bannerShopList(){
        return Response.success(bannerService.bannerShopList());
    }


    @ApiOperation(value = "实时金价", notes = "xx.xx格式")
    @GetMapping("/realTimeGoldPrice")
    public Response<String> realTimeGoldPrice(){
        return Response.success(    productService.realTimeGoldPrice());
    }
}
