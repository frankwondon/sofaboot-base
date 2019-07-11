package com.module.api.app.result;

import com.module.api.app.entity.AppCart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @ClassName AppCartResult
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/8 0008 11:01
 * @Version 1.0
 **/
@Data
@ApiModel("商品")
public class AppCartResult implements Serializable {
    @ApiModelProperty
    private Integer cartId;
    @ApiModelProperty("商品id")
    private Integer productId;
    @ApiModelProperty("模板id")
    private Integer skuId;
    @ApiModelProperty("数量")
    private Integer number;
    @ApiModelProperty("图片集")
    private String mainImg;
    @ApiModelProperty("缩略图")
    private String thumbImg;
    @ApiModelProperty("商品名")
    private String name;
    @ApiModelProperty("")
    private String sku;
    @ApiModelProperty("模板信息")
    private List skuList;
    @ApiModelProperty("单件标价")
    private BigInteger fixedPrice;
    @ApiModelProperty("总价格")
    private BigDecimal totalPrice;
    @ApiModelProperty("店铺名")
    private String shopName;
    @ApiModelProperty("库存量")
    private Integer reserve;

}
