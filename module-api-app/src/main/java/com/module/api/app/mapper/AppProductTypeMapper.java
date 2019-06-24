package com.module.api.app.mapper;

import com.module.api.app.entity.AppProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品分类 Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-14
 */
public interface AppProductTypeMapper extends BaseMapper<AppProductType> {
    int disable(@Param("id") Integer id, @Param("locked") Integer locked);

}
