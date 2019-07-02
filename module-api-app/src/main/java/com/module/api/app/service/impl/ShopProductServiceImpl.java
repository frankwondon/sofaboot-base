package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.dto.AppProductSkuDto;
import com.module.api.app.mapper.ProductSkuMapper;
import com.module.api.app.mapper.ShopProductMapper;
import com.module.api.app.query.ShopProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.service.ShopProductService;
import net.bytebuddy.asm.Advice;

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
    public IPage<AppProductResult> showPretermit(ShopProductQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        IPage<AppProductResult> appProductResultIPage=null;
        if (query.getSortId()==0){
           appProductResultIPage = shopProductMapper.showPretermitDESC(page);
        }
        if (query.getSortId()==1){
            appProductResultIPage = shopProductMapper.showPretermit(page);
        }
        for (AppProductResult appProductResult : appProductResultIPage.getRecords()) {
            appProductResult.setAppProductSku(productSkuMapper.productSkuByIdOne(appProductResult.getProductId()));
        }
        return appProductResultIPage;
    }

    @Override
    public IPage<AppProductResult> showPrice(ShopProductQuery query) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        IPage<AppProductResult> appProductResultIPage=null;
        if (query.getSortId()==0){
            appProductResultIPage = shopProductMapper.showPriceDESC(page);
        }
        if (query.getSortId()==1){
            appProductResultIPage = shopProductMapper.showPrice(page);
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
    private List<AppProductSkuDto> getSkuByProductId(Integer productId){
        List<AppProductSkuDto> appProductSkuDtoList = productSkuMapper.productSkuById(productId);
        if (appProductSkuDtoList!=null&&appProductSkuDtoList.size()>0){
            return appProductSkuDtoList;
        }
        return null;
    }
}
