package com.api.app.controller.order;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.api.app.intercept.AuthLogin;
import com.module.api.app.dto.ExpressPriceResult;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.query.ExpressPriceQuery;
import com.module.api.app.result.CreateOrderResult;
import com.module.api.app.result.OrderResult;
import com.module.api.app.service.AddressService;
import com.module.api.app.service.OrderService;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
import com.module.common.bean.BaseQuery;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
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
    @ApiOperation("创建订单")
    public Response<CreateOrderResult> createOrder(@RequestBody BaseQuery<List<CreateOrderQuery>> query,@ApiIgnore AppCurrentUser user){
        CreateOrderResult orderResult=new CreateOrderResult();
        List<OrderResult> order = orderService.createOrder(query.getData(),user.getId());
        orderResult.setOrders(order);
        orderResult.setDefaultAddress(addressService.findDefaultAddress(user.getId()));
        return Response.success(orderResult);
    }



    @PostMapping("computeExpressPrice")
    @ApiOperation("计算运费价格")
    public Response<List<ExpressPriceResult>> computeExpressPrice(@RequestBody BaseQuery<ExpressPriceQuery> query){
        return Response.success(orderService.computeExpressPrice(query.getData()));
    }
}
