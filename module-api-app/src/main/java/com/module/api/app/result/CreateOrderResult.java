package com.module.api.app.result;

import com.module.api.app.dto.AddressDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/8 13:50
 */
@ApiModel("创建订单结果")
@Data
public class CreateOrderResult implements Serializable {
    @ApiModelProperty("默认收货地址")
    private AddressDto defaultAddress;
    @ApiModelProperty("创建成功后的订单列表")
    List<OrderResult> orders;


}
