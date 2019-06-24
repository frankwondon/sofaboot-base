package com.module.api.app.result;

import com.module.api.app.entity.AppProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品的返回结果
 * @author wangdong
 * @date: 2019/6/24 13:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductResult extends AppProduct implements Serializable {
    /**
     * 商品中最低价格的SKU
     */
    private BigDecimal productPrice;
}
