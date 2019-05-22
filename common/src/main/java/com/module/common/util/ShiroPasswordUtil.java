package com.module.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroPasswordUtil {
    public static final String ALGORITHM="MD5";
    public static final int ITERATIONS=256;
    public static SaltPwdBean enpwd(){
        String autoPwd = "123456";//密码原值
        String salt =String.valueOf(System.currentTimeMillis()) ;//盐值
        String result = new SimpleHash(ALGORITHM,autoPwd,salt,ITERATIONS).toBase64();
        SaltPwdBean pwdBean =new SaltPwdBean(salt,autoPwd,result);
        return pwdBean;
    }
}
