package com.module.admin.app.dto;

import com.module.admin.app.entity.AppProduct;
import com.module.admin.app.entity.AppProductSku;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author wangdong
 * @date: 2019/6/17 9:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductDto extends AppProduct {
    @NotBlank(message = "SKU不能为空")
    private List<AppProductSku> productSkus;
    /**
     * 自动上架的时间String类型
     */
    private String autoShelfTimeStr;
}
