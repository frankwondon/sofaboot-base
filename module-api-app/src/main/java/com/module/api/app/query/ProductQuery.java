package com.module.api.app.query;

import com.module.common.bean.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangdong
 * @date: 2019/6/14 16:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("产品查询")
public class ProductQuery extends PageQuery {
    public ProductQuery(int page, int limit) {
        super(page, limit);
    }
    @ApiModelProperty(value = "类型ID",example = "1")
    private Integer typeId;
}
