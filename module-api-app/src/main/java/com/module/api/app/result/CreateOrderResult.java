package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/15 10:05
 */
@Data
@ApiModel("创建订单结果")
@Builder
public class CreateOrderResult implements Serializable {
    @ApiModelProperty("订单号")
    private String orderNum;
    @ApiModelProperty("订单ID")
    private Integer orderId;
}
