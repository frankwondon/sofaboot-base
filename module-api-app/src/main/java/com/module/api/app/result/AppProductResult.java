package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

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
    @ApiModelProperty(value = "精美，奢华图片路径",notes = "注意是,分割的多个图片相对路径")
    private String imgUrl;
    @ApiModelProperty("产品编号")
    private String productNum;
    @ApiModelProperty("产品编号")
    private String mainImg;
    @ApiModelProperty("产品编号")
    private String descImg;
    @ApiModelProperty("缩略图")
    private String thumbImg;


    

    @ApiModelProperty("商品标题")
    private String  descTitle;
    @ApiModelProperty("商品描述")
    private String  descText;


    @ApiModelProperty("多商品列表 商品的最低价格模板（商品最低价格）")
    private AppProductSkuResult appProductSku;

    @ApiModelProperty("产品详情 对应多个模板")
    private List<AppProductSkuResult> appProductSkuList;


    @ApiModelProperty("订单完成的销量")
    private Integer  purchases;

}
