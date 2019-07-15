package com.module.api.app.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/15 9:24
 */
@Data
@ApiModel("创建订单")
public class CreateOrderQuery implements Serializable {
    @ApiModelProperty("收货地址ID")
    @NotBlank(message = "收货地址不能为空")
    private Integer addressId;
    @ApiModelProperty("支付方式1支付宝2微信")
    @NotBlank(message = "支付方式不能为空")
    private Integer payType;
    @ApiModelProperty("下单时的备注信息")
    private String remark;
    @ApiModelProperty("需要下单SKU及数量集合")
    @Valid
    private List<ComputerProductPriceQuery> products;

}
