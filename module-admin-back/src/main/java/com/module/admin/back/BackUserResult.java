package com.module.admin.back;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BackUserResult implements Serializable {

    private Long id;
    /**
     * 用户名
     */
    private String username;



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
     * 商户ID随机生成
     */
    private String merchantId;

    /**
     * 乐观锁
     */
    private Integer revision;

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

    private String remark1;
}
