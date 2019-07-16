package com.module.api.app.result;

import cn.hutool.core.util.StrUtil;
import com.lib.db.entity.AppOrder;
import com.module.api.app.dto.AddressDto;
import com.module.api.app.entity.AppProduct;
import com.module.api.app.entity.AppProductSku;
import com.module.common.constant.ProductEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangdong
 * @date: 2019/7/8 13:50
 */
@ApiModel("订单详情")
@Data
public class OrderResult implements Serializable {

    @ApiModelProperty("订单Id")
    private Integer orderId;
    @ApiModelProperty("订单编号")
    private String orderNum;
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
    @ApiModelProperty(value = "SKU的数量")
    private Integer number;
    @ApiModelProperty("0包邮1自付运费2无需物流")
    private Integer expressType;
    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createTime;


    public OrderResult convertToResult(AppOrder order, AppProduct product, AppProductSku sku,Integer expressType){
        this.orderId=order.getId();
        this.orderNum=order.getOrderNum();
        this.productId=order.getProductId();
        this.productName=order.getProductName();
        this.productImg= StrUtil.split(product.getMainImg(),",")[0];
        this.productPrice=order.getProductPrice().longValue();
        this.productSumPrice=order.getProductSumPrice().longValue();
        this.skuId=order.getSkuId();
        this.sku=order.getSku();
        this.createTime=order.getCreateTime();
        this.number=order.getProductSize();
        this.expressType=expressType;
        return this;
    }


}
