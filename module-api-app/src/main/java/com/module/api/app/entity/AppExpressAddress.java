package com.module.api.app.entity;

import com.module.api.app.entity.base.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址管理
 * </p>
 *
 * @author wangdong
 * @since 2019-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppExpressAddress extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 省
     */
    private Integer provinceCode;

    /**
     * 市
     */
    private Integer cityCode;

    /**
     * 区县街道
     */
    private Integer zoneCode;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 0正常1删除
     */
    private Integer deleted;

    /**
     * 0普通1默认地址
     */
    private Integer addrDefault;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
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
