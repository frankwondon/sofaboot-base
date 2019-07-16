package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lib.db.entity.AppOrder;
import com.module.api.app.dto.PayDto;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName AppOrderMapper
 * @Description 订单
 * @Author YJT
 * @Date 2019/7/3 0003 16:07
 * @Version 1.0
 **/
public interface AppOrderMapper extends BaseMapper<AppOrder> {

   /**
    * 获取对应商品完成的订单
    * @param productId
    * @return
    */
   Integer countOrderById(Integer productId);

   /**
    * 获取订单号
    * @return
    */
   String callOrderNum();

   /**
    * 取消订单
    * 只有刚创建的订单可以被取消
    * @param userId
    * @param orderId
    * @return
    */
   int cancelOrder(@Param("userId") Integer userId, @Param("orderId")Integer orderId);

   /**
    * 获取支付实体
    * @param orderNum
    * @return
    */
   PayDto getPayDto(@Param("orderNum") String orderNum);
}
