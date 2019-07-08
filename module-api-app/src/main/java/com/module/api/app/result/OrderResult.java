package com.module.api.app.result;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangdong
 * @date: 2019/7/8 13:50
 */
@ApiModel("创建订单结果")
public class OrderResult implements Serializable {


    class OrderListResult{
        private String orderNum;
        private Integer productId;
        private Integer skuId;
        private LocalDateTime createTime;
    }
}
