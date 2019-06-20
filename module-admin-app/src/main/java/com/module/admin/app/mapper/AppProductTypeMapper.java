package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppProductType;
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
