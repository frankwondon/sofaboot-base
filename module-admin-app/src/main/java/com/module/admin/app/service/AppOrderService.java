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

    IPage<AppOrderResult> listOfOrder(AppOrderQuery query);



    IPage<AppOrderResult> orderList(AppOrderQuery query);

    IPage<AppOrderResult> queryByOrderId(String orderId);
}
