package com.lib.db.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.module.common.db.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wangdong
 * @since 2019-07-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppOrder extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 0子订单1父订单
     */
    private Integer parentOrder;
    /**
     * 父订单号
     */
    private String parentOrderNumber;

    private String orderNum;

    private Integer userId;

    private String sku;

    private Integer skuId;

    /**
     * 物流地址
     */
    private Integer expressAddressId;

    private Integer productId;

    private String productName;

    /**
     * 商品总价
     */
    private BigDecimal productSumPrice;

    /**
     * 商品的单价
     */
    private BigDecimal productPrice;

    /**
     * 物流价格
     */
    private BigDecimal expressPrice;

    /**
     * 所购买的商品数量
     */
    private Integer productSize;

    /**
     * 付款时的价格
     */
    private BigDecimal payPrice;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 支付渠道0无需支付1支付宝2微信
     */
    private Integer payChannel;

    /**
     * 订单状态0创建1付款中2付款完成3已发货4已收货5关闭订单
     */
    private Integer status;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 0个人发票,1公司发票
     */
    private Integer invoiceType;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
