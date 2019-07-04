package com.module.api.app.result;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @ClassName BannerListResult
 * @Description 轮播图结果
 * @Author Administrator
 * @Date 2019/7/1 0001 14:51
 * @Version 1.0
 **/

@Data
@Builder
@ApiModel("轮播图结果")
public class AppBannerListResult implements Serializable {
   @ApiModelProperty("商品名")
   private String name ;



   @ApiModelProperty("跳转链接")
   private String link;



   @ApiModelProperty(value = "图片路径")
   private String imgUrl;


   @ApiModelProperty("标题描述")
   private String descTitle;




   @ApiModelProperty("商品id")
   private Integer productId;

}
