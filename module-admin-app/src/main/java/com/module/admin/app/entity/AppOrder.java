package com.module.admin.app.entity;

import com.module.common.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 *
 * @author yjt
 * @since 2019-06-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppOrder extends BaseEntity {
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 商品编号
     */
    private int productId;
    /**
     * 物流地址
     */
    private String expressAddressId;
    /**
     * 原价
     */
    private double expressPrice;
    /**
     * 实付金额
     */
    private double payPrice;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 支付通道
     */
    private int payChannel;
    /**
     * 商品数量
     */
    private int productSize;


}
