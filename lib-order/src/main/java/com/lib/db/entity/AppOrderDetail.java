package com.lib.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.module.common.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author wangdong
 * @since 2019-07-09
 */
@Data
@Accessors(chain = true)
public class AppOrderDetail implements Serializable {

private static final long serialVersionUID=1L;
    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.INPUT)
    private Integer orderId;

    /**
     * 0父订单一键发货1子订单发货
     */
    private Integer shipType;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区县街道
     */
    private String zone;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 收件人电话
     */
    private String mobile;

    /**
     * 收件人姓名
     */
    private String perName;

    /**
     * 乐观锁
     */
    private Integer version;




}
