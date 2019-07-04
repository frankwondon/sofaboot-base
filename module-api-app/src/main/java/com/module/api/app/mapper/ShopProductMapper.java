package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.result.AppProductResult;

/**
 * @ClassName ShopProductMapper
 * @Description 商城产品
 * @Author YJT
 * @Date 2019/7/2 0002 14:33
 * @Version 1.0
 **/
public interface ShopProductMapper {
    /**
     * 默认加载 浏览量降序
     * @param page
     * @return
     */
    IPage<AppProductResult> showPretermitDESC(Page page);


    /**
     * 商品价格降序 （库存模板最低的作比较）
     * @param page
     * @return
     */
    IPage<AppProductResult> showPriceDESC(Page page);

    /**
     * 升序
     * @param page
     * @return
     */
    IPage<AppProductResult> showPrice(Page page);

    /**
     * 销量降
     * @param page
     * @return
     */
    IPage<AppProductResult> showPurchasesDESC(Page page);

    /**
     * 升
     * @param page
     * @return
     */
    IPage<AppProductResult> showPurchases(Page page);





}
