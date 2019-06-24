package com.module.admin.app.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/21 15:30
 */
@Data
public class UserManagerResult implements Serializable {

    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 银行卡认证
     */
    private String bankConfirm;

    /**
     * 0正常1锁定用户
     */
    private Integer locked;
}
