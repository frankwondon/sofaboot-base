package com.module.admin.cms.result;

import com.module.admin.cms.entity.CmsProduct;
import lombok.Data;

@Data
public class CmsProductResult extends CmsProduct {
    private String typeName;
}
