package com.module.base.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * @date: 2019/6/24 17:03
 */
@Data
public class LoginVerifyCodeDto implements Serializable {
    private String mobile;
    private String code;
}
