package com.module.admin.app.result;

import com.module.admin.app.entity.AppBanner;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangdong
 * @date: 2019/6/25 11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppRecommendResult extends AppBanner {
    /**
     * 商品名称
     */
    private String productName;
}
