package com.api.app.controller.order;

import com.api.app.intercept.AuthLogin;
import com.module.api.app.query.CreateOrderQuery;
import com.module.common.Response;
import com.module.common.bean.AppCurrentUser;
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

    @PostMapping("createOrder")
    @ApiOperation("创建订单")
    public Response createOrder(@RequestBody List<CreateOrderQuery> query){
        return Response.success(true);
    }


    @GetMapping("myOrders")
    @ApiOperation("我的订单")
    public Response myOrders(@ApiIgnore  AppCurrentUser user){
        return Response.success(true);
    }
}
