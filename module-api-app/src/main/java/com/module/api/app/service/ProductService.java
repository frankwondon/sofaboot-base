package com.module.api.app.service;

import com.module.api.app.query.ProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/25 10:20
 */
public interface ProductService {
    /**
     * 查询商品列表
     * @param query
     * @return
     */
    List<AppProductResult> listProduct(ProductQuery query);

    /**
     * 商品类型列表
     * @return
     */
    List<AppProductTypeResult> listType(Integer limit);
}
