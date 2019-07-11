package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.api.app.entity.AppExpressAddress;
import com.module.api.app.entity.AppExpressTemplate;
import com.module.api.app.entity.AppExpressTemplateArea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物流模板  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Repository
public interface ExpressTemplateMapper extends BaseMapper<AppExpressTemplate> {
    /**
     * 获取所有地区
     * @param tempId
     * @return
     */
    List<AppExpressTemplateArea> templateAreas(Integer tempId);

    /**
     * 获取运费模板的
     * @return
     */
    AppExpressTemplateArea defaultTemplateAreas(Integer tempId);
}
