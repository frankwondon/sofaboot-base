package com.module.api.app.service;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.query.GetOrderListQuery;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.OrderResult;

/**
 * @ClassName AppMineService
 * @Description 我的界面 接口
 * @Author YJT
 * @Date 2019/7/10 0010 9:03
 * @Version 1.0
 **/
public interface AppMineService {

   /**
    * 反馈意见
    * @param query
    * @return
    */
   boolean sendAdvice(SendAdviceQuery query);


   /**
    * 订单的查询
    * @param query
    * @param userId
    * @return
    */
   IPage<OrderResult> myOrderList(GetOrderListQuery query , Integer userId);
}
