package com.module.api.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.module.common.db.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author wangdong
 * @since 2019-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppOrderDetail extends BaseEntity {

private static final long serialVersionUID=1L;

    @TableId(value = "order_num", type = IdType.AUTO)
    private String orderNum;

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

    /**
     * 收货人电话
     */
    private Long mobile;


}
