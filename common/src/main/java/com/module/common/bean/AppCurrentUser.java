package com.module.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 全局当前用户对象
 * @author  wangdong
 */
@Data
public class AppCurrentUser implements Serializable {

    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String cellPhoneNum;






}
