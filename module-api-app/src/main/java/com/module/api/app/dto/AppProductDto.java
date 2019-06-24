package com.module.api.app.dto;

import com.module.api.app.entity.AppProduct;
import com.module.api.app.entity.AppProductSku;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author wangdong
 * @date: 2019/6/17 9:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductDto extends AppProduct {
    private List<AppProductSku> productSkus;
}
