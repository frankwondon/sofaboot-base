package com.module.base.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/25 9:17
 */
public enum  SmsPrefix {

    LOGIN_VERIFY_CODE("LOGIN_VERIFY_CODE:","登陆验证码"),
    RPWD_CODE("RPWD_CODE:","修改密码验证码");
    private String key;
    private String desc;
    SmsPrefix(String key, String desc){
        this.key=key;
        this.desc=desc;
    }
    public String key(){
        return key;
    }
}
