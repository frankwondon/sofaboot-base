package com.module.api.app.service.impl;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.mapper.AppMineMapper;
import com.module.api.app.query.GetOrderListQuery;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.OrderResult;
import com.module.api.app.service.AppMineService;
import com.module.common.ResponseCode;
import com.module.common.constant.RegexPattern;
import com.module.common.exception.DBException;
import com.module.common.exception.ValidationException;

import javax.annotation.Resource;

/**
 * @ClassName AppMineServiceImpl
 * @Description 我的 界面  逻辑层
 * @Author YJT
 * @Date 2019/7/10 0010 9:04
 * @Version 1.0
 **/

public class AppMineServiceImpl implements AppMineService {
    @Resource
    private AppMineMapper appMineMapper;

    @Override
    public boolean sendAdvice(SendAdviceQuery query) {
        //todo  意见文本内容敏感信息
        if (1!=1){
            throw new DBException(ResponseCode.C_520009,"反馈内容敏感");
        }
        if (!ReUtil.isMatch(RegexPattern.MOBILE, query.getCellPhone())) {
            throw new ValidationException(ResponseCode.C_500003, "手机号格式错误");
        }
        int a=0;
        a=appMineMapper.sendAdvice(query);
        if (a>0){
            return true;
        }else {
            throw new DBException(ResponseCode.C_520008,"意见反馈不成功");
        }

    }

    @Override
    public IPage<OrderResult> myOrderList(GetOrderListQuery query, Integer userId) {
        //分页
        Page page=new Page(query.getPage(),query.getLimit());
        //返回    类型订单分页
        IPage<OrderResult> orderResultIPage = appMineMapper.myOrderList(page, query.getType(), userId);
        if (orderResultIPage.getRecords().size()>0){
            for (OrderResult orderResult : orderResultIPage.getRecords()) {
                //todo 店铺名称后期可拓展
                orderResult.setShopName("陕西金韵文化有限公司");
                orderResult.setProductImg(orderResult.getMainImg().split(",")[0]);
            }
        }
        return orderResultIPage;
    }
}
