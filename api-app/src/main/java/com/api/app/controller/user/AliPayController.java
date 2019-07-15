package com.api.app.controller.user;

import com.alipay.api.AlipayApiException;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.service.AliService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("pay")
@Api(tags = "支付接口")
public class AliPayController {
    @SofaReference
    private AliService ordersService;




    @ResponseBody
    @GetMapping("/ali")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNum"  ,paramType = "query",required = true,value = "生成的唯一商戶订单号")
    })
    public Response ali(String orderNum) throws  AlipayApiException {

        return Response.success(ordersService.ali(orderNum));
    }

}
