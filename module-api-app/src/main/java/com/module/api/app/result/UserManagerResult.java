package com.module.api.app.result;

import lombok.Data;

/**
 * @author wangdong
 * @date: 2019/6/21 15:30
 */
@Data
public class UserManagerResult {
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
