package com.module.admin.back.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.module.admin.back.entity.base.BaseEntity;
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
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BackUser extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 用户名
     */
    private String username;


    /**
     * 盐值 新建或修改时的随机字符串
     */
    private String salt;

    /**
     * 是否锁定
     */
    private Integer locked;

    /**
     * 手机号
     */
    private String cellPhoneNum;

    /**
     * 加盐后的密码
     */
    private String encryptPwd;

    /**
     * 用户类型 0后台1商户
     */
    private Integer userType;

    /**
     * 角色
     */
    private Integer roleId;

    /**
     * 商户ID随机生成
     */
    private String merchantId;


    @TableField("remark_1")
    private String remark1;


}
