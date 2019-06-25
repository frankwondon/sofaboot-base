package com.module.api.app.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 商品的返回结果
 * @author wangdong
 * @date: 2019/6/24 13:51
 */
@Data
@ApiModel("商品类型")
public class AppProductTypeResult implements Serializable {

    @ApiModelProperty("类型ID")
    private Integer typeId;

    @ApiModelProperty("类型名称")
    private String name;

    @ApiModelProperty("默认选中项第一页的数据")
    private List<AppProductResult> firstDefaultPage= Collections.emptyList();
}
