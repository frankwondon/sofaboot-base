package com.module.api.app.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.module.common.db.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wangdong
 * @since 2019-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppOrder extends BaseEntity {

private static final long serialVersionUID=1L;

    @TableId(value = "order_num", type = IdType.AUTO)
    private String orderNum;

    private Integer productId;

    private Integer skuId;

    /**
     * 物流地址
     */
    private Integer expressAddressId;

    /**
     * 物流价格
     */
    private BigDecimal expressPrice;

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
     * 所购买的商品数量
     */
    private Integer productSize;

    /**
     * 订单状态0创建1付款中2付款完成3已发货4已收货5关闭订单
     */
    private Integer status;

    /**
     * 订单的sku也是JSON格式
     */
    private String sku;

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
