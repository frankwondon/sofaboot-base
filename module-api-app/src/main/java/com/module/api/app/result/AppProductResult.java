package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 商品的返回结果
 * @author wangdong
 * @date: 2019/6/24 13:51
 */
@Data
@ApiModel("商品")
public class AppProductResult implements Serializable {
    @ApiModelProperty("商品ID")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String  productName;
    @ApiModelProperty(value = "图片路径",notes = "注意是,分割的多个图片相对路径")
    private String imgUrl;
}
