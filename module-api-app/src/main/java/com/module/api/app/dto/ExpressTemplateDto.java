package com.module.api.app.dto;

import com.module.api.app.entity.AppExpressTemplate;
import com.module.api.app.entity.AppExpressTemplateArea;
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
