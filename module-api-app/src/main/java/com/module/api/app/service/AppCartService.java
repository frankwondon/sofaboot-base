package com.module.api.app.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.dto.DelCartDto;
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
     * 详情   添加购物车
     * @param appCart
     */
    long addToCart(AppCart appCart);

    /**
     * 推荐  加入购物车
     * @param appCart
     * @return
     */
    long putToCart(AppCart appCart);

    /**
     * 删除购物车 返回剩余购物车数量
     * @param delCartDto
     * @return
     */
    long delCartList(DelCartDto delCartDto);

}
