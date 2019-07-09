package com.api.app.controller.cart;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.dto.DelCartDto;
import com.module.api.app.entity.AppCart;
import com.module.api.app.result.AppCartResult;
import com.module.api.app.service.AppCartService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AppCartController
 * @Description 购物车
 * @Author YJT
 * @Date 2019/7/4 0004 13:47
 * @Version 1.0
 **/
@RestController
@RequestMapping("cart")
@Api(tags = "购物车订单")
public class AppCartController {


    @SofaReference
    private AppCartService appCartService;


    @ApiOperation(value = "购物车列表")
    @GetMapping("cartList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",paramType = "query",required = true,value = "起始页"),
            @ApiImplicitParam(name = "limit",paramType = "query",required = true,value = "每页个数")
    })
    public Response<IPage<AppCartResult>> cartList( PageQuery query, @ApiIgnore AppCurrentUser user){
        IPage<AppCartResult> appCartResultIPage = appCartService.appCartList(query, user.getId());
        return Response.success(appCartResultIPage);
    }

    @ApiOperation(value = "详情页加入购物车  用于商品数量的改变（不能为0）")
    @PostMapping("addToCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",required = true,value = "商品id"),
            @ApiImplicitParam(name = "skuId",required = true,value = "库存模板id"),
            @ApiImplicitParam(name = "number",required = true,value = "加入购物车的数量")
    })
    public Response<Long> addToCart(@RequestBody AppCart appCart, @ApiIgnore AppCurrentUser user){
        appCart.setUserId(user.getId());
        long cartCount = appCartService.addToCart(appCart);
        return Response.success(cartCount);
    }
    @ApiOperation(value = "推荐页加入购物车")
    @PostMapping("putToCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",required = true,value = "商品id"),
            @ApiImplicitParam(name = "skuId",required = true,value = "库存模板id"),
            @ApiImplicitParam(name = "number",required = true,value = "加入购物车的数量")
    })
    public Response<Long> putToCart(@RequestBody AppCart appCart, @ApiIgnore AppCurrentUser user){
        appCart.setUserId(user.getId());
        long cartCount = appCartService.putToCart(appCart);
        return Response.success(cartCount);
    }

    @ApiOperation(value = "编辑删除")
    @PostMapping(value = "delCartList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuIds",required = true,value = "多模板id")
    })
    public Response delCartList(@RequestBody DelCartDto delCartDto, @ApiIgnore AppCurrentUser user){
        delCartDto.setUserId(user.getId());
        long cartCount=appCartService.delCartList(delCartDto);
        return Response.success(cartCount);
    }

}
