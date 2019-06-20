package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     * 商品列表
     * @param page
     * @param status
     * @param keyWord
     * @return
     */
    IPage<AppProduct> list(Page page, @Param("status")Integer status,@Param("keyWord")String keyWord);

    /**
     * 调用商品编号生成存储过程
     * @param id 模板的ID 1是商品的
     * @return
     */
    String callProductNum(Integer id);

    /**
     * 上下架
     * @return
     */
    int updateShelfAndObtained(@Param("productId") Integer productId,@Param("status") Integer status);
}
