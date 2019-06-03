package com.module.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密工具
 *@author wangdong
 *@date 2019/6/3
 */
public class ShiroPasswordUtil {
    public static final String ALGORITHM="MD5";
    public static final int ITERATIONS=256;

    /**
     * 用于生成一个salt+pwd的盐值加密结果
     * @param pwd
     * @return
     */
    public static SaltPwdBean encpwd(String pwd){
        String salt =String.valueOf(System.currentTimeMillis()) ;//盐值
        String result = new SimpleHash(ALGORITHM,pwd,salt,ITERATIONS).toBase64();
        return new SaltPwdBean(salt,pwd,result);
    }


    /**
     * 预先知道salt盐的情况下，生成密码，一般用于校验密码
     * @param pwd
     * @param salt
     * @return
     */
    public static String encpwd(String pwd,String salt){
        return new SimpleHash(ALGORITHM,pwd,salt,ITERATIONS).toBase64();
    }
}
