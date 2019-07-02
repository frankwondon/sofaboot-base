package com.module.api.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/2 10:52
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("收货地址")
@Data
public class AddressDto extends BaseDto implements Serializable {


    /**
     * 省
     */
    @ApiModelProperty(value = "省CODE",example = "100001")
    @Min(value = 0,message = "省级CODE必须是数字类型,如100001")
    private Integer provinceCode;

    /**
     * 市
     */
    @ApiModelProperty(value = "市CODE",example = "100001")
    @Min(value = 0,message = "市级CODE必须是数字类型,如100011")
    private Integer cityCode;

    /**
     * 区县街道
     */
    @ApiModelProperty(value = "区县CODE",example = "100001")
    @Min(value = 0,message = "区县级CODE必须是数字类型,如100111")
    private Integer zoneCode;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "地址不能为空")
    private String detail;

    /**
     * 0普通1默认地址
     */

    @ApiModelProperty(value = "0普通1默认地址",example = "0")
    private Integer addrDefault;
}
