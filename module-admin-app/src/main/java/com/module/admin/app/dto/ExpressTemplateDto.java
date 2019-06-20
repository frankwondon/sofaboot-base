package com.module.admin.app.dto;

import com.module.admin.app.entity.AppExpressTemplate;
import com.module.admin.app.entity.AppExpressTemplateArea;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/19 14:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExpressTemplateDto extends AppExpressTemplate {
    private List<AppExpressTemplateArea> area;
}
