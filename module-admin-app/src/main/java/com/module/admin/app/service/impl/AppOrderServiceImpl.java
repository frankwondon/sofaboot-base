package com.module.admin.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppOrder;
import com.module.admin.app.mapper.AppOrderMapper;
import com.module.admin.app.query.AppOrderQuery;
import com.module.admin.app.result.AppOrderResult;
import com.module.admin.app.service.AppOrderService;
import com.module.common.util.CheckSearchUtil;

import javax.annotation.Resource;

public class AppOrderServiceImpl implements AppOrderService {
    @Resource
    private AppOrderMapper appOrderMapper;

    /**
     * OK
     * @param OrderId   根据id查询
     * @return
     */
    @Override
    public IPage<AppOrderResult> queryByOrderId(String OrderId) {
        return appOrderMapper.orderListByOrderId(OrderId);
    }

    /**
     *
     * @param query   有关键字  根据关键字查询
     * @return
     */
    @Override
    public IPage<AppOrderResult> listOfOrder(AppOrderQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
            /**汉字查询*/
        if (CheckSearchUtil.isChinese(query.getKeyWord())){
            query.setKeyWord("%"+query.getKeyWord()+"%");
            return appOrderMapper.orderListByName(page,query.getStatus(),query.getKeyWord(),query.getStartTime(),query.getEndTime());
        }
            /**手机号查询*/
        else if (CheckSearchUtil.isMobile(query.getKeyWord())){
           return appOrderMapper.orderListByPhone(page,query.getStatus(),query.getKeyWord(),query.getStartTime(),query.getEndTime());
        }
            /**订单号查询*/
        else if (CheckSearchUtil.isOrderId(query.getKeyWord())){
           return appOrderMapper.orderListByOrderId(query.getKeyWord());
        }
        return null;
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
