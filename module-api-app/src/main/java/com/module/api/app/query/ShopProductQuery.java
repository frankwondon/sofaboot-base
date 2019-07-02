package com.module.api.app.query;

import com.module.common.bean.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName ShopProductQuery
 * @Description 商城商品查询 （条件）
 * @Author YJT
 * @Date 2019/7/2 0002 14:54
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("排序查询")
public class ShopProductQuery extends PageQuery {
    public ShopProductQuery(int page, int limit) {
        super(page, limit);
    }
    @ApiModelProperty(value = "排序",example = "1")
    private Integer sortId;
}
