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
 * @since 2019-07-03
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
     * 收货人名称
     */
    private String receiptUserName;

    /**
     * 收货人手机号
     */
    private String receiptUserMobile;

    /**
     * 省名字
     */
    private String provinceName;

    /**
     * 省
     */
    private Integer provinceCode;

    /**
     * 市名字
     */
    private String cityName;

    /**
     * 市
     */
    private Integer cityCode;

    /**
     * 地区名字
     */
    private String zoneName;

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



}
