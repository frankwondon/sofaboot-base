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
     * 实时金价
     * @return
     */
    String realTimeGoldPrice();
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

    /**
     * 搜索
     * @param keyWord
     * @return
     */
    List<AppProductResult> searchKeyWord(String keyWord);

    /**
     * todo 商品详情
     * @param productId
     * @return
     */
    AppProductResult getProductById(Integer productId);

    /**
     * 精品小件
     * @return
     */
    List<AppProductResult> competitiveList();

    /**
     * 奢华产品
     * @return
     */
    List<AppProductResult> luxuriousList();
    /**
     * 随便看看
     * @return
     */
    List<AppProductResult> casualList();
}
