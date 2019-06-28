package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.query.AppOrderQuery;
import com.module.admin.app.result.AppOrderResult;

/**
 * 订单
 * @author yjt
 * @date: 2019/6/27
 */
public interface AppOrderService {

    IPage<AppOrderResult> listOfOrder();


    IPage<AppOrderResult> listOfOrderByName(AppOrderQuery query);
    IPage<AppOrderResult> listOfOrderByMobile(AppOrderQuery query);

    AppOrderResult queryByOrderId(String orderId);
    IPage<AppOrderResult> orderList(AppOrderQuery query);
}
