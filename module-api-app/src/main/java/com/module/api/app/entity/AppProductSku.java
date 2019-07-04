package com.module.api.app.entity;

import java.math.BigDecimal;

import com.module.api.app.entity.base.BaseEntity;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("商品库存模板")
public class AppProductSku extends BaseEntity {

private static final long serialVersionUID=1L;

    @ApiModelProperty("模板id")
    private Integer skuId;
    /**
     * 定义的单项格式类似[{name:'克重',value:0.5},{name:'材质',value:‘Au999’}]
     */
    @ApiModelProperty("模板")
    private String sku;

    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    private Integer productId;

    /**
     * 标价
     */
    @ApiModelProperty("标价")
    private BigDecimal markedPrice;

    /**
     * 定价
     */
    @ApiModelProperty("定价")
    private BigDecimal fixedPrice;

    /**
     * 库存
     */
    @ApiModelProperty("库存量")

    private Integer reserve;






}
