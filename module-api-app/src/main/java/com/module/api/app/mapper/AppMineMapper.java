package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.query.SendAdviceQuery;
import com.module.api.app.result.OrderListResult;
import com.module.api.app.result.OrderResult;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    List<SendAdviceQuery> selectList(Wrapper<SendAdviceQuery> queryWrapper);

    /**
    * 根据状态查询父订单
    * @param type
    * @param userId
    * @return
    */
    IPage<String> getParentOrderNum(Page page,@Param("type") Integer type, @Param("userId") Integer userId);

   /**
    * 我的订单查询
    * @param page
    * @param type
    * @param userId
    * @return
    */
   IPage<OrderResult> myOrderList(Page page, @Param("type") Integer type, @Param("userId") Integer userId);


    OrderResult getSunOrderList(@Param("orderNum") List s);


    /**
     * 待付款
     * @param page
     * @param userId
     * @return
     */
    IPage<OrderListResult> getDfkOrderList(Page page,@Param("userId") Integer userId);


    /**
     * 代发货
     * @param page
     * @param userId
     * @return
     */
    IPage<OrderListResult> getDfhOrderList(Page page,@Param("userId") Integer userId);

}
