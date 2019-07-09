package com.module.admin.app.entity;

import com.module.common.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * back_user 
 * </p>
 *
 * @author wangdong
 * @since 2019-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppUser extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 盐值 新建或修改时的随机字符串
     */
    private String salt;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 是否锁定
     */
    private Integer locked;

    /**
     * 0正常 1已删除
     */
    private Integer deleted;

    /**
     * 手机号
     */
    private String cellPhoneNum;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 0男1女
     */
    private Integer sex;

    /**
     * 0未确认1已确认
     */
    private Integer bankConfirm;

    /**
     * f
     */
    private String bankId;

    /**
     * 商户ID随机生成
     */
    private String merchantId;

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
