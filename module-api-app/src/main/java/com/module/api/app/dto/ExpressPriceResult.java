package com.module.api.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangdong
 * @date: 2019/7/10 14:12
 */
@Data
@ApiModel("计算运费结果")
public class ExpressPriceResult {
    @ApiModelProperty("订单Id")
    private Integer orderId;
    @ApiModelProperty("产品ID")
    private Integer productId;
    @ApiModelProperty("运费价格")
    private Long expressPrice;
    @ApiModelProperty("可以配送true否则false")
    private Boolean canDelivery=true;


}
