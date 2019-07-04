package com.module.api.app.mapper;

/**
 * @ClassName AppOrderMapper
 * @Description 订单
 * @Author YJT
 * @Date 2019/7/3 0003 16:07
 * @Version 1.0
 **/
public interface AppOrderMapper {

   /**
    * 获取对应商品完成的订单
    * @param productId
    * @return
    */
   Integer countOrderById(Integer productId);
}
