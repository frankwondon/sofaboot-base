package com.module.api.app.service.impl;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.mapper.AppMineMapper;
import com.module.api.app.query.GetOrderListQuery;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.OrderListResult;
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
    public  IPage<OrderListResult> myOrderList(GetOrderListQuery query, Integer userId) {
        //获取分页不同状态父订单号
        Integer status = query.getType();
        IPage<String> parentOrderNum = getParentOrderNum(query,userId);
        Page page= new Page(query.getPage(),query.getLimit());

        //某状态下 该用户无订单信息返回
        if (parentOrderNum.getRecords()==null||parentOrderNum.getRecords().size()==0){
            throw new DBException(ResponseCode.C_520010);
        }
        //待付款
        if (status==0){
          return dfk(page,userId);
        }
        //代发货
        if (status==1){
            return dfh(page,userId);
        }
        //待收货
        if(status==2){
          return  null;
        }
        //已完成
        if(status==3){

        }
        //全部
        if (status==4){

        }


        //返回    类型订单分页
        IPage<OrderResult> orderResultIPage = appMineMapper.myOrderList(page, query.getType(), userId);
        if (orderResultIPage.getRecords().size()>0){
            for (OrderResult orderResult : orderResultIPage.getRecords()) {

                orderResult.setProductImg(orderResult.getMainImg().split(",")[0]);
            }

        }
        return  null;
    }

    //代发货 结果集
    public IPage<OrderListResult> dfh(Page page,Integer userId){
        IPage<OrderListResult> dfhOrderList = appMineMapper.getDfhOrderList(page,userId);
        if (dfhOrderList.getRecords().size()>0){
            for (OrderListResult orderListResult : dfhOrderList.getRecords()) {
                for (OrderResult orderResult : orderListResult.getOrderResultList()) {

                    orderResult.setProductImg(orderResult.getMainImg().split(",")[0]);
                }
                //todo 店铺名称后期可拓展
                orderListResult.setShopName("陕西金韵文化有限公司");
            }
        }
        return dfhOrderList;
    }


    //待付款 结果集
    public IPage<OrderListResult> dfk(Page page,Integer userId){
        IPage<OrderListResult> dfkOrderList = appMineMapper.getDfkOrderList(page,userId);
        if (dfkOrderList.getRecords().size()>0){
            for (OrderListResult orderListResult : dfkOrderList.getRecords()) {
                for (OrderResult orderResult : orderListResult.getOrderResultList()) {

                    orderResult.setProductImg(orderResult.getMainImg().split(",")[0]);
                }
                //todo 店铺名称后期可拓展
                orderListResult.setShopName("陕西金韵文化有限公司");
            }
        }
        return dfkOrderList;
    }




    //使用userId 完成父订单查询 分页
    public IPage<String> getParentOrderNum(GetOrderListQuery query,Integer userId){
        Page page=new Page(query.getPage(),query.getLimit());
        IPage<String> parentOrderNum = appMineMapper.getParentOrderNum(page,query.getType(), userId);
        return parentOrderNum;
    }

}
