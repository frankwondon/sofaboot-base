package com.module.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于生成加盐的数据库密码
 * @author  wangdong
 */
@Data
@AllArgsConstructor
public class SaltPwdBean implements Serializable {
    private String salt;
    private String pwd;
    private String saltPwd;


}
