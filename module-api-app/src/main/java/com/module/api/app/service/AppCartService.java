package com.module.api.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.entity.AppCart;
import com.module.api.app.result.AppCartResult;
import com.module.common.bean.PageQuery;

/**
 * @ClassName AppOrderService
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/4 0004 14:11
 * @Version 1.0
 **/
public interface AppCartService {

    /**
     * 购物车查询回显
     * @param query
     * @param userId
     * @return
     */
   IPage<AppCartResult> appCartList(PageQuery query, Integer userId);


    /**
     * 添加购物车
     * @param appCart
     */
   void addToCart(AppCart appCart);

    void delCartList(Integer[] skuIds,Integer userId);

}
