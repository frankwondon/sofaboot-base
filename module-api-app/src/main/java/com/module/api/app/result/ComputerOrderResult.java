package com.module.api.app.result;

import cn.hutool.core.util.StrUtil;
import com.lib.db.entity.AppOrder;
import com.module.api.app.entity.AppProduct;
import com.module.api.app.entity.AppProductSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangdong
 * @date: 2019/7/8 13:50
 */
@ApiModel("计算订单价格结果")
@Data
@Builder
public class ComputerOrderResult implements Serializable {

    @ApiModelProperty("商品ID")
    private Integer productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品图片")
    private String productImg;
    @ApiModelProperty("商品单价")
    private Long productPrice;
    @ApiModelProperty("商品总价")
    private Long productSumPrice;
    @ApiModelProperty(value = "商品SKUID")
    private Integer skuId;
    @ApiModelProperty(value = "商品SKU详细信息")
    private String sku;
    @ApiModelProperty("购物车ID,没有就是-1")
    private Integer cartId;
    @ApiModelProperty(value = "SKU的数量")
    private Integer number;
    @ApiModelProperty("0包邮1自付运费2无需物流")
    private Integer expressType;
    @ApiModelProperty("0正常 1库存为0 2库存不足预购买的商品 3该SKU已经失效")
    private Integer productSkuStatus;



}
