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
 * @date: 2019/7/10 14:27
 */
@Data
@ApiModel("计算运费价格请求")
public class ExpressPriceQuery implements Serializable {
    @NotBlank(message = "收货地址ID不能为空")
    @ApiModelProperty(value = "收货地址ID",example = "1")
    private Integer addressId;

    @ApiModelProperty("需要计算运费的商品信息")
    @Valid
    private List<ComputerProductPriceQuery> products;

}
