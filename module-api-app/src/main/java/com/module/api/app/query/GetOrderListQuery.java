package com.module.api.app.query;

import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @ClassName GetOrderListQuery
 * @Description 订单类型查询
 * @Author YJT
 * @Date 2019/7/11 0011 17:18
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("订单类型查询")
public class GetOrderListQuery extends PageQuery {
    public GetOrderListQuery(int page, int limit) {
        super(page, limit);
    }
    @ApiModelProperty("订单类型0待付款1代发货2待收货3已完成4全部订单")
    private Integer type;

}
