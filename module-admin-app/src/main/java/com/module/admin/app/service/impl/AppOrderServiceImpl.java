package com.module.admin.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppOrder;
import com.module.admin.app.mapper.AppOrderMapper;
import com.module.admin.app.query.AppOrderQuery;
import com.module.admin.app.result.AppOrderResult;
import com.module.admin.app.service.AppOrderService;
import javax.annotation.Resource;

public class AppOrderServiceImpl implements AppOrderService {
    @Resource
    private AppOrderMapper appOrderMapper;

    @Override
    public IPage<AppOrderResult> listOfOrder() {
        return null;
    }

    @Override
    public IPage<AppOrderResult> listOfOrderByName(AppOrderQuery query) {
        Page<AppOrder> page = new Page<>(query.getPage(), query.getLimit());
        return appOrderMapper.orderListByName(page,query.getStatus(),query.getKeyWord(),query.getStartTime(),query.getEndTime());
    }

    @Override
    public IPage<AppOrderResult> listOfOrderByMobile(AppOrderQuery query) {
        Page<AppOrder> page = new Page<>(query.getPage(), query.getLimit());
        return appOrderMapper.orderListByPhone(page,query.getStatus(),query.getKeyWord(),query.getStartTime(),query.getEndTime());
    }

    /**
     * OK
     * @param OrderId   根据id查询
     * @return
     */
    @Override
    public AppOrderResult queryByOrderId(String OrderId) {
        return appOrderMapper.orderListByOrderId(OrderId);
    }


    /**
     *   OK
     * @param query   无条件可有时间限制查询
     * @return
     */
    @Override
    public IPage<AppOrderResult> orderList(AppOrderQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        return appOrderMapper.orderList(page,query.getStatus(),query.getStartTime(),query.getEndTime());
    }
}
