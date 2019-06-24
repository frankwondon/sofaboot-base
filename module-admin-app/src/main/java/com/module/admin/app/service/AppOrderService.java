package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.result.AppOrderResult;

/**
 * 订单
 * @author wangdong
 * @date: 2019/6/24 16:41
 */
public interface AppOrderService {

    IPage<AppOrderResult> listOfOrder();
}
