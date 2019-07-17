package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel("订单结果")
public class OrderListResult implements Serializable {
    @ApiModelProperty("父订单号" )
    private String parentOrderNum;
    @ApiModelProperty("父订单对应子订单的list结果")
    private List<OrderResult> orderResultList;
    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty("商品总价")
    private Long productSumPrice;
    @ApiModelProperty("剩余时间  秒")
    private BigInteger remainTime;
    @ApiModelProperty("商家名称")
    private String shopName;
    @ApiModelProperty("发货类型 0一键发货1子单分开发货")
    private String shipType;
    @ApiModelProperty(value = "订单状态   0,1为待付款，2为待发货，3为待收货，4为已完成")
    private Integer orderStatus;


}
