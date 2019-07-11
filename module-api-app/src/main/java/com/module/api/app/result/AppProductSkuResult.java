package com.module.api.app.result;

import com.module.api.app.entity.AppProductSku;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AppProductSkuResult
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/11 0011 13:13
 * @Version 1.0
 **/

@Data
public class AppProductSkuResult extends AppProductSku {
    @ApiModelProperty("模板id")
    private Integer skuId;

    /**
     * sku模板list
     */
    private List skuList;
}
