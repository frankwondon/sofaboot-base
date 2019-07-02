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
    IPage<AppProductResult> showPretermitDESC(Page page);
    IPage<AppProductResult> showPretermit(Page page);



    IPage<AppProductResult> showPriceDESC(Page page);
    IPage<AppProductResult> showPrice(Page page);


}
