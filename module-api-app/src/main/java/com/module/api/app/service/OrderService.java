package com.module.api.app.service;

import com.module.api.app.dto.ExpressPriceResult;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.query.ExpressPriceQuery;
import com.module.api.app.result.OrderResult;

import java.util.List;

/**
 * 订单服务
 * @author wangdong
 * @date: 2019/7/8 11:45
 */
public interface OrderService {
    /**
     * 创建订单
     * @param query
     * @return
     */
    List<OrderResult> createOrder(List<CreateOrderQuery> query,Integer userId);

    /**
     * 计算运费价格
     * @param query
     * @return
     */
    List<ExpressPriceResult> computeExpressPrice(ExpressPriceQuery query);
}
