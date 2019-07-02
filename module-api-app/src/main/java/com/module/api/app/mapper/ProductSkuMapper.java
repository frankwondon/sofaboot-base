package com.module.api.app.mapper;

import com.module.api.app.dto.AppProductSkuDto;

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
     *  查询id
     *  返回商品模板，标价，定价，库存量
     * @Rule 正常商品 标价倒序 库存>0  标价>0
     * @param productId
     * @return
     */
    List<AppProductSkuDto> productSkuById(Integer productId);
    /**
     *  查询id
     *  返回商品模板，标价，定价，库存量
     * @Rule 正常商品 标价倒序 库存>0  标价>0
     * @param productId
     * @return
     */
    AppProductSkuDto productSkuByIdOne(Integer productId);
}