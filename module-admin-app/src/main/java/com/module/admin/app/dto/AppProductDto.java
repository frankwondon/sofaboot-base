package com.module.admin.app.dto;

import com.module.admin.app.entity.AppProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author wangdong
 * @date: 2019/6/17 9:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductDto extends AppProduct {
    /**前端生成的SKU*/
    private String finalSku;
}
