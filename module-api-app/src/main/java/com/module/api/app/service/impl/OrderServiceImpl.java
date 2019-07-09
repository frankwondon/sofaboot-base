package com.module.api.app.service.impl;

import com.module.api.app.mapper.ProductMapper;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.result.OrderResult;
import com.module.api.app.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/7/9 14:17
 */
public class OrderServiceImpl implements OrderService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public OrderResult createOrder(List<CreateOrderQuery> query) {
        for (CreateOrderQuery createOrderQuery : query) {
            //减库存
            if (productMapper.cutSkuReserve(createOrderQuery) > 0) {
                //生成订单号
                //创建订单
            }
        }
        //计算价格
        return null;
    }

}
