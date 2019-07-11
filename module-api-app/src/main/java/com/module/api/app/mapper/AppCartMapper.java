package com.module.api.app.mapper;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.dto.DelCartDto;
import com.module.api.app.entity.AppCart;
import com.module.api.app.result.AppCartResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AppCartMapper
 * @Description TODO
 * @Author YJT
 * @Date 2019/7/4 0004 17:28
 * @Version 1.0
 **/
public interface AppCartMapper extends BaseMapper<AppCart> {

    /**
     * 添加购物车
     * @param appCart
     * @return
     */
   int newAddToCart(AppCart appCart);

    /**
     * 只作为改变数量
     * @param appCart
     * @return
     */
   int updateCart(@Param("appCart") AppCart appCart);


    /**
     * 通过用户名 模板id 商品id 完成判断是否存在该用户名下的对应模板商品
     * @param productId
     * @param skuId
     * @param userId
     * @return
     */
   AppCart getOldCartByParam(@Param("productId")Integer productId,@Param("skuId")Integer skuId,@Param("userId")Integer userId);


    /**
     * 显示登陆用户的购物车列表（分页显示）
     * @param page
     * @param userId
     * @return
     */
    IPage<AppCartResult> appCartList(Page page, @Param("userId") Integer userId);


    /**
     * 购物车批量编辑  删除
     * @param delMap
     * @return rows
     */
    int delCartBySkuIds(@Param("delMap") Map delMap);


    /**
     * 根据用户信息查询购物车数量
     * @param userId
     * @return
     */
    long getCartCount(@Param("userId") Integer userId);
}
