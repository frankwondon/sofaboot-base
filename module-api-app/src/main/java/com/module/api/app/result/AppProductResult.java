package com.module.api.app.result;

import com.module.api.app.dto.AppProductSkuDto;

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
    @ApiModelProperty(value = "图片路径",notes = "注意是,分割的多个图片相对路径")
    private String imgUrl;
    @ApiModelProperty("产品编号")
    private String productNum;
    




    @ApiModelProperty("商品标题")
    private String  descTitle;
    @ApiModelProperty("商品描述")
    private String  descText;


    @ApiModelProperty("一个商品获得最低价格")
    private AppProductSkuDto appProductSku;

    @ApiModelProperty("商品id 对应多个模板")
    private List<AppProductSkuDto> appProductSkuList;



}
