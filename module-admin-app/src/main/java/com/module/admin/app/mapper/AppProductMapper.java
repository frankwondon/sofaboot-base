package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.entity.AppProductAdditionOne;
import com.module.admin.app.result.AppProductResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-14
 */
public interface AppProductMapper extends BaseMapper<AppProduct> {
    /**
     * 插入扩展表
     * @param additionOne
     */
    void  insertAppProductAdditionOne(AppProductAdditionOne additionOne);
    /**
     * 商品列表
     * @param page
     * @param status
     * @param keyWord
     * @return
     */
    IPage<AppProductResult> listOfShelf(Page page, @Param("status") Integer status, @Param("keyWord") String keyWord);


    IPage<AppProductResult> listOfBuyStatus(Page page, @Param("keyWord") String keyWord, @Param("reserve") Integer reserve);

    /**
     * 计数库存<指定数的
     * @param reserve
     * @return
     */
    Integer countOfBuyStatus(@Param("reserve") Integer reserve);

    /**
     * 调用商品编号生成存储过程
     * @param id 模板的ID 1是商品的
     * @return
     */
    String callProductNum(Integer id);

    /**
     * 上下架
     * @param productId
     * @param status
     * @return
     */
    int updateShelfAndObtained(@Param("productId") Integer productId, @Param("status") Integer status);

    /**
     * 根据商品编号和商品名称模糊查询5条商品信息
     * @param keyWord
     * @return
     */
    List<AppProduct> likeSearchProduct(String keyWord);


}
