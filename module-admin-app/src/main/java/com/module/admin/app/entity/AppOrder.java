package com.module.admin.app.entity;

import com.module.admin.app.entity.base.BaseEntity;
import com.module.admin.app.query.AppOrderQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;


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
    private String order_num;
    /**
     * 商品编号
     */
    private int product_id;
    /**
     * 物流地址
     */
    private String express_address_id;
    /**
     * 原价
     */
    private double express_price;
    /**
     * 实付金额
     */
    private double pay_price;
    /**
     * 订单状态
     */
    private int status;
    /**
     * 支付通道
     */
    private int pay_channel;
    /**
     * 商品数量
     */
    private int product_size;


}
