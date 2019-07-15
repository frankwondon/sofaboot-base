package com.module.api.app.dto;


import lombok.Data;

/**
 * 支付实体对象
 */
@Data
public class PayDto {
    /**
     * 商品编号
     */
    private String productNum;
    /**
     *  订单编号 out_trade_no  唯一订单号（父订单）
     */
    private String orderNum;

    /**
     * subject   交易标题 使用商品描述标题
     */
    private String productTitle;

    /**
     * total_amount 总金额0.01
     */
    private String totalAmount;

}
