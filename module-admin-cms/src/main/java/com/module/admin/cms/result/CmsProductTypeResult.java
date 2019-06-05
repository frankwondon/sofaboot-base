package com.module.admin.cms.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.entity.CmsProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CmsProductTypeResult extends CmsProductType {
    private IPage<CmsProduct> productPage;
}
