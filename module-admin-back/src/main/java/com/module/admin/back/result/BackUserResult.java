package com.module.admin.back.result;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
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

    private Integer roleId;
    private String role;
    /**
     * 用户类型 0后台1商户
     */
    private Integer userType;

    /**
     * 商户ID随机生成
     */
    private String merchantId;

    /**备注1*/
    private String remark1;


}
