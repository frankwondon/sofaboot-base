package com.module.api.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.query.ShopProductQuery;
import com.module.api.app.result.AppProductResult;

/**
 * @ClassName ShopProductService
 * @Description 商城页加载商品
 * @Author YJT
 * @Date 2019/7/2 0002 14:28
 * @Version 1.0
 **/
public interface ShopProductService {
   IPage<AppProductResult> showPretermit(ShopProductQuery query);


   IPage<AppProductResult> showPrice(ShopProductQuery query);


}
