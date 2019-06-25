package com.module.api.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/25 13:21
 */
public interface ProductMapper {
    /**
     * 分页查询商品列表
     * @param page
     * @param typeId
     * @return
     */
    List<AppProductResult> pageQuery(Page page,@Param("typeId") Integer typeId);

    /**
     * 查询前台显示的产品类型
     * @return
     */
    List<AppProductTypeResult> listProductType();
}
