package com.module.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroPasswordUtil {
    public static final String ALGORITHM="MD5";
    public static final int ITERATIONS=256;
    public static SaltPwdBean enpwd(String pwd){
        String salt =String.valueOf(System.currentTimeMillis()) ;//盐值
        String result = new SimpleHash(ALGORITHM,pwd,salt,ITERATIONS).toBase64();
        SaltPwdBean pwdBean =new SaltPwdBean(salt,pwd,result);
        return pwdBean;
    }
}
