package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.entity.AppProductSku;
import com.module.api.app.mapper.ProductSkuMapper;
import com.module.api.app.mapper.ShopProductMapper;
import com.module.api.app.query.ShopProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.service.ShopProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ShopProductServiceImpl
 * @Description 商城商品提供方接口
 * @Author YJT
 * @Date 2019/7/2 0002 14:29
 * @Version 1.0
 **/
public class ShopProductServiceImpl implements ShopProductService {
    @Resource
    private ShopProductMapper shopProductMapper;
    @Resource
    private ProductSkuMapper productSkuMapper;

    @Override
    public IPage<AppProductResult> showSortList(ShopProductQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        //0默认初始加载 1价格升2价格降 3销量降4销量升
        IPage<AppProductResult> appProductResultIPage=null;
        if (query.getSortId()==0){
           appProductResultIPage = shopProductMapper.showPretermitDESC(page);
        }
        if (query.getSortId()==1){
            appProductResultIPage = shopProductMapper.showPrice(page);
        }
        if (query.getSortId()==2){
            appProductResultIPage = shopProductMapper.showPriceDESC(page);
        }
        if (query.getSortId()==3){
            appProductResultIPage = shopProductMapper.showPurchasesDESC(page);
        }
        if (query.getSortId()==4){
            appProductResultIPage = shopProductMapper.showPurchases(page);
        }
        for (AppProductResult appProductResult : appProductResultIPage.getRecords()) {
            appProductResult.setAppProductSku(productSkuMapper.productSkuByIdOne(appProductResult.getProductId()));
        }
        return appProductResultIPage;
    }





    /**
     *  通过id 返回该商品库存信息
     * @param productId
     * @return
     */
    private List<AppProductSku> getSkuByProductId(Integer productId){
        List<AppProductSku> appProductSkuDtoList = productSkuMapper.productSkuById(productId);
        if (appProductSkuDtoList!=null&&appProductSkuDtoList.size()>0){
            return appProductSkuDtoList;
        }
        return null;
    }
}
