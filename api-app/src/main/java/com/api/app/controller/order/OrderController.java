package com.api.app.controller.order;

import com.alipay.api.AlipayApiException;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthLogin;
import com.api.app.intercept.RateLimiter;
import com.module.api.app.dto.ExpressPriceResult;
import com.module.api.app.query.ComputerProductPriceQuery;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.query.ExpressPriceQuery;
import com.module.api.app.result.ComputerOrderResult;
import com.module.api.app.result.ComputerProductPrice;
import com.module.api.app.result.CreateOrderResult;
import com.module.api.app.service.AddressService;
import com.module.api.app.service.OrderService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.BaseQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/8 13:30
 */
@RestController
@RequestMapping("order")
@Api(tags = "订单接口")
@AuthLogin
public class OrderController {
    @SofaReference
    private OrderService orderService;
    @SofaReference
    private AddressService addressService;
    @PostMapping("createOrder")
    @ApiOperation("创建订单 ")
    @RateLimiter(limit = 0.1)
    public Response<CreateOrderResult> createOrder(@RequestBody BaseQuery<CreateOrderQuery> query, @ApiIgnore AppCurrentUser user){
        CreateOrderResult orderResult= orderService.createOrder(query.getData(),user.getId());
        return Response.success(orderResult);
    }


    @PostMapping("computerProductPrice")
    @ApiOperation("计算商品价格 ")
    public Response<ComputerProductPrice> computerProductPrice(@RequestBody BaseQuery<List<ComputerProductPriceQuery>> query, @ApiIgnore AppCurrentUser user){
        ComputerProductPrice orderResult=new ComputerProductPrice();
        List<ComputerOrderResult> computerOrderResults = orderService.computerOrderResults(query.getData());
        orderResult.setProductResults(computerOrderResults);
        orderResult.setDefaultAddress(addressService.findDefaultAddress(user.getId()));
        return Response.success(orderResult);
    }

    @PostMapping("cancelOrder")
    @ApiOperation("取消订单")
    public Response<List<ExpressPriceResult>> cancelOrder(@RequestBody BaseQuery<Integer> orderId,@ApiIgnore AppCurrentUser user){
        orderService.cancelOrder(orderId.getData(),user.getId());
        return Response.success(true);
    }

    @PostMapping("computeExpressPrice")
    @ApiOperation("计算运费价格")
    public Response<List<ExpressPriceResult>> computeExpressPrice(@RequestBody BaseQuery<ExpressPriceQuery> query){
        return Response.success(orderService.computeExpressPrice(query.getData()));
    }



    @ResponseBody
    @GetMapping("/ali")
    @ApiOperation("订单支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNum"  ,paramType = "query",required = true,value = "父订单号 生成的唯一商戶订单号")
    })
    public Response ali(String orderNum)  {
        return Response.success(orderService.ali(orderNum));
    }

}
