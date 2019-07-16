package com.module.api.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/7/2 10:52
 */
@ApiModel("收货地址")
@Data
public class AddressDto implements Serializable {

    @ApiModelProperty(value = "id一般修改时用到",example = "1")
    private Integer addressId;

    /**
     * 收货人名称
     */
    @ApiModelProperty(value = "收货人")
    @NotBlank(message = "收货人不能为空")
    private String receiptUserName;

    /**
     * 收货人手机号
     */
    @ApiModelProperty(value = "手机号")
    @Length(min = 11,max = 11,message = "手机号必须是11位手机号")
    private String receiptUserMobile;


    /**
     * 省
     */
    @ApiModelProperty(value = "省CODE",example = "100001")
    @Min(value = 0,message = "省级CODE必须是数字类型,如100001")
    private Integer provinceCode;

    @ApiModelProperty(value = "省Name")
    @NotBlank(message = "省Name不能为空")
    private String provinceName;

    /**
     * 市
     */
    @ApiModelProperty(value = "市CODE",example = "100001")
    @Min(value = 0,message = "市级CODE必须是数字类型,如100011")
    private Integer cityCode;

    @ApiModelProperty(value = "市Name")
    @NotBlank(message = "市Name不能为空")
    private String cityName;

    /**
     * 区县街道
     */
    @ApiModelProperty(value = "区县CODE",example = "100001")
    @Min(value = 0,message = "区县级CODE必须是数字类型,如100111")
    private Integer zoneCode;

    @ApiModelProperty(value = "区县Name")
    @NotBlank(message = "区县Name不能为空")
    private String zoneName;

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
