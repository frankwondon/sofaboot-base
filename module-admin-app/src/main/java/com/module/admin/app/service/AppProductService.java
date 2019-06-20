package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.dto.AppProductDto;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.query.AppProductQuery;

/**
 *
 *
 * @author wangdong
 * @date  2019/6/14
 */
public interface AppProductService {
    /**
     * 商品列表
     * @param pageQuery
     * @return
     */
     IPage<AppProduct> list(AppProductQuery pageQuery);

    /**
     * 商品编号
     * @return
     */
    String productNum();
    /**
     * 生成商品
     * 步骤:
     * 1验证各个属性值
     * 2验证SKU组合正确性
     * 3保存SKU
     * 4保存商品
     */
    void create(AppProductDto productDto);


    /**
     * 获取更新时的回显数据
     * @param productId
     * @return
     */
    AppProductDto findUpdateDetail(Integer productId);

    void update(AppProductDto productDto);
    /**
     * 上下架
     * @param productId
     * @param status 0上架1下架
     */
    void shelfAndObtained(Integer productId,Integer status);
}
