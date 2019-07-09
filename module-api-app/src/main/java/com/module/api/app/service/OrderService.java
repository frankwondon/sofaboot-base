package com.module.api.app.service;

import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.result.OrderResult;

/**
 * @author wangdong
 * @date: 2019/7/8 11:45
 */
public interface OrderService {
    /**
     * 创建订单
     * @param query
     * @return
     */
    OrderResult createOrder(CreateOrderQuery query);


}