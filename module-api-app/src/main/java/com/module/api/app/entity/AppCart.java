package com.module.api.app.entity;

import com.module.common.db.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName AppCart
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/5 0005 9:11
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("购物车")
public class AppCart extends BaseEntity{

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Integer productId;
    /**
     * 模板id
     */
    @ApiModelProperty("模板id")
    private Integer skuId;
    /**
     * 购买数量
     */
    @ApiModelProperty("购买数量")
    @Min(value = 1,message = "添加购买最少1")
    private Integer number;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userId;

}
