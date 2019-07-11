package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.entity.AppProduct;
import com.module.api.app.query.CreateOrderQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/25 13:21
 */
@Repository
public interface ProductMapper extends BaseMapper<AppProduct> {
    /**
     * 分页查询商品列表
     *
     * @param page
     * @param typeId
     * @return
     */
    List<AppProductResult> pageQuery(Page page, @Param("typeId") Integer typeId);

    /**
     * 查询前台显示的产品类型
     *
     * @return
     */
    List<AppProductTypeResult> listProductType();

    /**
     * 搜索
     *
     * @param keyWord
     * @return
     */
    List<AppProductResult> searchKeyWord(@Param("keyWord") String keyWord);

    /**
     * @param productId
     * @return
     */
    AppProductResult getProductById(@Param("productId") Integer productId);


    /**
     * 随便看看
     *
     * @return
     */
    IPage<AppProductResult> casualList(Page page);


    /**
     *  减库存
     * @param orderQuery
     * @return
     */
    int cutSkuReserve(CreateOrderQuery orderQuery);

}
