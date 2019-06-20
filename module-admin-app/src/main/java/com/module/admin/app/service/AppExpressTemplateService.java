package com.module.admin.app.service;

import com.module.admin.app.dto.ExpressTemplateDto;

import java.util.List;

/**
 *运费模板
 *@author wangdong
 *@date 2019/6/14
 */
public interface AppExpressTemplateService {
    /**
     * 查询所有快递模板
     * @return
     */
    List<ExpressTemplateDto> listTemplate();

    /**
     * 创建模板
     * @param dto
     */
    void createTemplate(ExpressTemplateDto dto);

    /**
     * 模板详情
     * @param productId
     * @return
     */
    ExpressTemplateDto templateDetail(Integer productId);

    /**
     * 更新运费模板
     * 有上架商品不允许更新
     * @param dto
     */
    void updateTemplate(ExpressTemplateDto dto);

    /**
     * 删除运费模板
     * 已经存在绑定商品不允许删除
     * @param tempId
     */
    void  delTemplate(Integer tempId);
}
