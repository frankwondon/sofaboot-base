package com.module.admin.cms.result;

import com.module.admin.cms.entity.CmsProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsProductResult extends CmsProduct {
    private String typeName;
}
