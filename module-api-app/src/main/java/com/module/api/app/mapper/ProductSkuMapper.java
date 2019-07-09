package com.module.api.app.mapper;

import com.module.api.app.entity.AppProductSku;

import java.util.List;

/**
 * @ClassName ProductSkuMapper
 * @Description 库存商品的查询
 * @Author YJT
 * @Date 2019/7/2 0002 9:50
 * @Version 1.0
 **/
public interface ProductSkuMapper {

    /**
     *  查询商品id
     *  返回商品模板，标价，定价，库存量   多个模板
     * @Rule 正常商品 标价倒序 库存>0  标价>0
     * @param productId
     * @return
     */
    List<AppProductSku> productSkuById(Integer productId);
    /**
     *  查询商品id
     *  返回商品模板，标价，定价，库存量  单个模板
     * @Rule 正常商品 标价倒序 库存>0  标价>0
     * @param productId
     * @return
     */
    AppProductSku productSkuByIdOne(Integer productId);
}
