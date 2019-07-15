package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.OrderResult;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName AppMineMapper
 * @Description 我的页面
 * @Author YJT
 * @Date 2019/7/11 0011 15:27
 * @Version 1.0
 **/
public interface AppMineMapper extends BaseMapper<SendAdviceQuery>{
   /**
    * 意见反馈
    * @param query
    * @return
    */
   int sendAdvice(@Param("advice") SendAdviceQuery query);


   /**
    * 我的订单查询
    * @param page
    * @param type
    * @param userId
    * @return
    */
   IPage<OrderResult> myOrderList(Page page, @Param("type") Integer type, @Param("userId") Integer userId);
}
