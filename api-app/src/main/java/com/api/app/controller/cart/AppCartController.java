package com.api.app.controller.cart;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.entity.AppCart;
import com.module.api.app.result.AppCartResult;
import com.module.api.app.service.AppCartService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
            @ApiImplicitParam(name = "page",paramType = "query",required = false,value = "起始页"),
            @ApiImplicitParam(name = "limit",paramType = "query",required = false,value = "每页个数")
    })
    public Response<IPage<AppCartResult>> cartList(PageQuery query, @ApiIgnore AppCurrentUser user){
      return Response.success(appCartService.appCartList(query,user.getId()));
    }

    @ApiOperation(value = "加入购物车")
    @PutMapping("addToCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId",required = true,value = "商品id"),
            @ApiImplicitParam(name = "skuId",required = true,value = "库存模板id"),
            @ApiImplicitParam(name = "number",required = true,value = "加入购物车的数量")

    })
    public Response addToCart(AppCart appCart, @ApiIgnore AppCurrentUser user){
        appCart.setUserId(user.getId());
        appCartService.addToCart(appCart);
        return Response.success(true);
    }

    @ApiOperation(value = "编辑删除 开发中")
    @DeleteMapping(value = "delCartList")
    public Response delCartList(@ApiParam("商品skuId数组") @RequestParam Integer[] skuIds, @ApiIgnore AppCurrentUser user){

        appCartService.delCartList(skuIds,user.getId() );
        return Response.success("");
    }


}
