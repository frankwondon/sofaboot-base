package com.module.admin.app.result;

import com.module.admin.app.entity.AppProduct;
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

    /**
     * 商品中sku库存为0的数量
     */
    private Integer skuZeroReserve;
}
