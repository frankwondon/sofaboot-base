package com.module.api.app.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/8 13:40
 */
@Data
@ApiModel("创建订单商品信息")
public class CreateOrderQuery implements Serializable {

    @ApiModelProperty(value = "商品ID")
    @NotBlank(message = "商品ID不能为空")
    private Integer productId;

    @ApiModelProperty(value = "skuId")
    @NotBlank(message = "SKU不能为空")
    private Integer skuId;

    @ApiModelProperty(value = "购买数量")
    @Min(value = 1, message = "购买数量必须大于0")
    @Max(value = 10000, message = "购买数量必须小于10000")
    private Integer number;
    @ApiModelProperty(value = "购物车ID,可为空")
    private Integer cartId;
}
