package com.module.admin.app.mapper;

import com.module.admin.app.entity.AppExpressTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物流模板  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Repository
public interface AppExpressTemplateMapper extends BaseMapper<AppExpressTemplate> {
    /**
     * 查询正在上架中的商品是否使用此模板
     * @param tempId
     * @return
     */
    int countShelfProductUseTemplate(Integer tempId);


    /**
     * 查询所有商品是否使用此模板
     * @param tempId
     * @return
     */
    int countAllProductUseTemplate(Integer tempId);
}
