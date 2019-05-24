package com.module.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 全局当前用户对象
 * @author  wangdong
 */
@Data
public class CurrentUser implements Serializable {
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String cellPhoneNum;

    /**
     * 用户类型 0后台1商户
     */
    private Integer userType;

    /*用户角色*/
    private Integer roleId;




}
