package com.api.back.controller.app;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppExpressTemplateArea;
import com.module.admin.app.query.AppOrderQuery;
import com.module.admin.app.query.AppProductQuery;
import com.module.admin.app.result.AppOrderResult;
import com.module.admin.app.result.AppProductResult;
import com.module.admin.app.service.AppOrderService;
import com.module.common.Response;
import com.module.common.util.CheckSearchUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "订单管理")
@RestController
@RequestMapping("/appadmin/order/index")
public class AppOrderController {

    @SofaReference
    private AppOrderService appOrderService;

    @PostMapping("list")
    @ApiOperation(value = "订单管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status"  ,paramType = "query",required = true,value = "0全部订单1待付款订单2待发货3已发货4已完成5已关闭"),
            @ApiImplicitParam(name = "page"  ,paramType = "query",required = true,value = "当前页"),
            @ApiImplicitParam(name = "limit" ,paramType = "query" ,required = true,value = "每页条数"),
            @ApiImplicitParam(name = "keyWord" ,paramType = "query" ,required = true,value = "模糊查询条件"),
            @ApiImplicitParam(name = "startTime" ,paramType = "query" ,required = true,value = "起始时间"),
            @ApiImplicitParam(name = "endTime" ,paramType = "query" ,required = true,value = "结束时间")
    })
    public Response<IPage<AppOrderController>> orderList(AppOrderQuery query){
        if (query.getKeyWord()!=null&&!"".equals(query.getKeyWord())){
            if (CheckSearchUtil.isMobile(query.getKeyWord())){
                return Response.success(appOrderService.listOfOrderByMobile(query));
            }
            if (CheckSearchUtil.isChinese(query.getKeyWord())){
                return Response.success(appOrderService.listOfOrderByName(query));
            }
            if (CheckSearchUtil.isOrderId(query.getKeyWord())){
                return Response.success(appOrderService.queryByOrderId(query.getKeyWord()));
            }
        }
        if (query.getKeyWord()==null||"".equals(query.getKeyWord().trim())){
            IPage<AppOrderResult> appOrderResultIPage = appOrderService.orderList(query);
            return Response.success(appOrderService.orderList(query));
        }
        return Response.success("无效查询");
    }
    @PostMapping("getOrder")
    @ApiOperation(value = "订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId" ,paramType = "query" ,required = true,value = "订单单号"),
    })
    public Response<AppOrderResult> queryByOrderId(String orderId){
        return Response.success(appOrderService.queryByOrderId(orderId));
    }

}
